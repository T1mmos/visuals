package gent.timdemey.visuals.core.cmd;

import gent.timdemey.visuals.core.log.Debuggable;
import gent.timdemey.visuals.core.log.elements.DebugElement;
import gent.timdemey.visuals.core.log.elements.StringDebugElement;

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
