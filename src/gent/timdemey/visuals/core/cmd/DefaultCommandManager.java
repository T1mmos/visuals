package gent.timdemey.visuals.core.cmd;

import java.util.PriorityQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import gent.timdemey.visuals.core.concurrent.ConcurrentEntryListener;
import gent.timdemey.visuals.core.concurrent.ConcurrentEntryModel;
import gent.timdemey.visuals.core.log.msg.BugMsg;
import gent.timdemey.visuals.core.log.msg.ErrorMsg;
import gent.timdemey.visuals.core.log.msg.InfoMsg;
import gent.timdemey.visuals.core.util.Log;
import gent.timdemey.visuals.core.util.Unchecked;
import gent.timdemey.visuals.core.util.XThreadFactory;

/**
 * The framework core's default command manager.
 * @author Timmos
 */
public class DefaultCommandManager implements CommandManager {

    /** The service that executes the incoming commands. */
    private final ExecutorService cmdExecutingService;
    /** The service on which callback code runs whenever a command has completely executed. */
    private final ExecutorService cmdListenerService;
    private final ConcurrentEntryModel<CommandTracker<?>> concListenerModel;
    private final AtomicInteger callCnt;

    /**
     * Creates a new command manager.
     */
    public DefaultCommandManager() {
        ThreadFactory thrFact_cmdExec = new XThreadFactory("Command Executor");
        ThreadFactory thrFact_cmdList = new XThreadFactory("Command Execution Listener");

        this.cmdExecutingService = new PrioritizedThreadPoolExecutor(2, thrFact_cmdExec);
        this.cmdListenerService = new ThreadPoolExecutor(2, Integer.MAX_VALUE,
                        1, TimeUnit.SECONDS,
                        new SynchronousQueue<Runnable>(),
                        thrFact_cmdList);
        this.concListenerModel = new ConcurrentEntryModel<>();
        this.callCnt = new AtomicInteger();
    }

    @Override
    public void add(ConcurrentEntryListener<CommandTracker<?>> listener) {
        Unchecked.nullPtr(listener);
        concListenerModel.addListener(listener);
    }

    @Override
    public void remove(ConcurrentEntryListener<CommandTracker<?>> listener) {
        concListenerModel.removeListener(listener);
    }

    @Override
    public <T> CommandTracker<T> submit(Command<T> call) {
        return submit(call, null); // call.getClass().getSimpleName()
    }

    @Override
    public <T> CommandTracker<T> submit(Command<T> call, String name) {
        Callable<T> callable = new CallableCommandWrapper<>(call);
        Future<T> future = cmdExecutingService.submit(callable);
        int id = callCnt.getAndIncrement();

        CommandTracker<T> trackable = new CommandTracker<>(id, name, call, future);

        Runnable tracer = new TrackableTracer(trackable);
        cmdListenerService.submit(tracer);

        concListenerModel.addEntry(trackable);

        return trackable;
    }

    private static class CallableCommandWrapper<T> implements Callable<T> {

        private final Command<T> command;

        private CallableCommandWrapper(Command<T> command) {
            this.command = command;
        }

        @Override
        public T call() throws Exception {
            try {
                return command.execute();
            } catch (RuntimeException rte) {
                Log.error(ErrorMsg.COMMAND_UNCHECKED_EXCEPTION);
                Log.error(rte);
                throw rte;
            }
        }
    }

    private class TrackableTracer implements Runnable {

        private final CommandTracker<?> trackable;

        private TrackableTracer(CommandTracker<?> trackable) {
            this.trackable = trackable;
        }

        @Override
        public void run() {
            try {
                trackable.getFuture().get();
                Log.trace(InfoMsg.TRACKABLE_OK, trackable);
            } catch (CancellationException ex) {
                Log.info(InfoMsg.TRACKABLE_CANCELED, trackable);
            } catch (ExecutionException ex) {
                Log.error(ErrorMsg.TRACKABLE_FUTURE, trackable);
                Log.error(ex);
            } catch (InterruptedException ex) {
                // the traceServ is private and one cannot access its threads, so this should never happen.
                Log.bug(BugMsg.TRACKABLE_TRACER_INTERRUPT, trackable);
            }
        }
    }

    /**
     * Helper class that makes it possible to use a {@link PriorityBlockingQueue} in a {@link ThreadPoolExecutor}, due to the
     * fact that it implements the {@link Comparable} interface.
     * <p>A {@code ThreadPoolExecutor}'s {@link ThreadPoolExecutor#newTaskFor newTaskFor} method returns a {@link FutureTask} which is the
     * wrapper around anything being submitted into the thread pool executor (being a {@link Runnable} or a {@link Callable}).
     * To use a {@code PriorityBlockingQueue}, these entities need to implement the {@code Comparable} interface in order for
     * the work queue to be able to sort them.
     * @author Timmos
     *
     * @param <T> the type the command returns
     */
    private static final class ComparableFutureTask<T> extends FutureTask<T> implements Comparable<ComparableFutureTask<T>> {

        private final CallableCommandWrapper<T> wrap;

        private ComparableFutureTask(CallableCommandWrapper<T> wrap) {
            super(wrap);
            this.wrap = wrap;
        }

        @Override
        public int compareTo(ComparableFutureTask<T> o) {
            int mine = ComparableFutureTask.getValue(this.wrap.command.getPriority());
            int other = ComparableFutureTask.getValue(o.wrap.command.getPriority());

            return mine - other;
        }

        /**
         * Converts the given {@link Priority} into an integer value which is used to
         * sort an item in a {@link PriorityQueue}. The head of such queue
         * is the <i>least</i> item - so lower priority comes with increasing integer values.
         * @param p the priority of a command
         * @return an integer representing the order towards a {@link PriorityQueue} - higher means
         * lower priority
         */
        private static int getValue(Priority p) {
            switch (p) {
                case BACKGROUND:
                    return 100;
                case LOADING:
                    return 50;
                case RESPONSIVE:
                    return 0;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /**
     * A thread pool executor that wraps incoming {@link Callable}s in a {@link ComparableFutureTask},
     * instead of its superclass {@link FutureTask}. This enables the tasks to be compared.
     * @author Timmos
     */
    private static final class PrioritizedThreadPoolExecutor extends ThreadPoolExecutor {

        private PrioritizedThreadPoolExecutor(int poolSize, ThreadFactory threadFactory) {
            super(poolSize, poolSize, 0L, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>(), threadFactory);
        }

        @Override
        protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
            return new ComparableFutureTask<>((CallableCommandWrapper<T>) callable);
        }

        @Override
        protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
            throw new UnsupportedOperationException(); // we only allow CallableCommandWrapper, which is a Callable
        }
    }
}
