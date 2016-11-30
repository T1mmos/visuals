package gent.timdemey.visuals.core.log.elements;

import java.util.LinkedHashMap;
import java.util.Map;

import gent.timdemey.visuals.core.log.Debuggable;

/**
 * Builds a {@link CompositeDebugElement}.
 * @author Timmos
 */
public final class DebugBuilder {

    private final Map<String, DebugElement> children = new LinkedHashMap<String, DebugElement>();

    /**
     * Constructor.
     */
    public DebugBuilder () {
    }

    /**
     * Adds a new {@link DebugElement} to the to-be-created {@link CompositeDebugElement}. The passed
     * object's class is inspected to achieve the best result:
     * <ul>
     * <li>If it is an instance of {@link Debuggable}, the object's {@link Debuggable#getDebugInfo()} is
     * used (which returns a {@link DebugElement})
     * <li>If it is a supported type (String, Integer, ...) it is wrapped into a {@link StringDebugElement}, ...
     * <li>A {@code null} object result in a {@link NullDebugElement}
     * <li>Any other object is wrapped in a {@link UnknownDebugElement}.
     * </ul>
     * @param name how the child object is named
     * @param obj the actual child object
     * @return {@code this}, the builder, so to allow method chaining
     */
    public DebugBuilder add (String name, Object obj) {
        if (obj == null) {
            children.put(name, new NullDebugElement());
        } else  if (obj instanceof Debuggable) {
            children.put(name, ((Debuggable) obj).getDebugInfo());
        } else if (obj instanceof String) {
            children.put(name, new StringDebugElement((String) obj));
        } else if (obj instanceof Integer) {
            children.put(name,  new IntegerDebugElement((Integer) obj));
        } else if (obj instanceof Boolean) {
            children.put(name, new BooleanDebugElement((Boolean) obj));
        } else {
            children.put(name, new UnknownDebugElement(obj));
        }

        return this;
    }

    /**
     * Instantiates and returns a {@link CompositeDebugElement} using all added child objects.
     * @return a {@link CompositeDebugElement}
     */
    public CompositeDebugElement build () {
        return new CompositeDebugElement(children);
    }
}