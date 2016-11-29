/**
 * The pluggable command execution system.
 * <p>
 * A command execution system aims to process units of work in a separate command layer. The benefits of this approach
 * are:
 * <ul>
 * <li><b>Centralized execution: </b>all work is done in the command layer. As such, the layer can be instructed in
 * different ways, e.g. by a GUI on top of it, or by incoming network events, or by a simple command line.
 * <li><b>Extensible: </b>by using the Command design pattern, new units of work can be added very easily by extending
 * the abstract class {@link core.cmd.Command Command}.
 * <li><b>No code duplication: </b>The implementation of higher layers that instruct the command layer are minimal and
 * do not need duplication of "worker" code.
 * </ul>
 * Commands can be executed directly, but a far more flexible use is to post them on a {@link core.cmd.CommandManager
 * CommandManager} which will execute the command on a worker thread and allows to track the command throughout
 * execution, cancel the command, prioritize and order commands, etc.
 * @author Timmos
 */
package core.cmd;