package gent.timdemey.visuals.core.util;

import java.util.concurrent.ThreadFactory;

/**
 * A thread factory.
 * @author Timmos
 */
public final class XThreadFactory implements ThreadFactory {

    private final String prefix;
    
    private int _thrCnt = 0;
    
    /**
     * Creates a new thread factory, creating threads with a default name prefix.
     */
    public XThreadFactory (){
        this ("XThread (default)");
    }
    
    /**
     * Creates a new thread factory, creating threads with the given prefix.
     * @param prefix the prefix for each thread name
     */
    public XThreadFactory (String prefix){
        if (prefix == null)
            throw new NullPointerException();
        this.prefix = prefix;
    }
    
    @Override
    public Thread newThread (Runnable r) {
        if (r == null)
            throw new NullPointerException();
        
        Thread t = new Thread(r, prefix + " #" + _thrCnt++);
        
        // TODO set some useful properties on the thread, e.g. an uncaughtExceptionHandler
        t.setDaemon(true);
        return t;
    }
}
