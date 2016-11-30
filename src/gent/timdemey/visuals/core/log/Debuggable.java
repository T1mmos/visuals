package gent.timdemey.visuals.core.log;

import gent.timdemey.visuals.core.log.elements.DebugElement;

/**
 * A class implementing this interface exposes {@link DebugElement a raw representation of an instance}, used
 * to nicely format objects and entire object trees.
 * @author Timmos
 */
public interface Debuggable {
    
    /**
     * Returns an object holding runtime information about this {@code Debuggable}, useful for logging and debugging.
     * @return an structure representing {@code this}, in raw format
     */
    public DebugElement getDebugInfo ();
}
