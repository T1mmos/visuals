package core.cmd;

import core.log.Debuggable;
import core.log.elements.DebugElement;
import core.log.elements.StringDebugElement;

/**
 * Represents the priority of a command. Command priorities may influence
 * the behavior of a {@link CommandManager}, although that is not
 * mandatory behavior.
 * @author Timmos
 */
public enum Priority implements Debuggable {
    /** Represents the priority of a command that is not urgent at all. */
    BACKGROUND,
    /** Represents the priority of a command that is not urgent, but must execute
     * somewhere in the near future, as the user might wait for results. */
    LOADING,
    /** Represents the priority of a command that must be processed immediately. */
    RESPONSIVE;

    @Override
    public DebugElement getDebugInfo() {
        return new StringDebugElement(this.name());
    }
}
