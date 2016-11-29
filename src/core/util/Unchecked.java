package core.util;

import javax.swing.SwingUtilities;

/**
 * A utility class bundling methods that throw unchecked exceptions when their parameters do not pass some check.
 * @author Timmos
 */
public class Unchecked { // Uncheck my heart... baby let me be... set me free
    
    private Unchecked () {
    }
    
    /**
     * Throws a {@link NullPointerException} without message if {@code o} is {@code null}. 
     * @param o the reference to check for nullness
     */
    public static void nullPtr (Object o){
        if (o == null)
            throw new NullPointerException();
    }
    
    /**
     * Throws a {@link NullPointerException} with the given message if {@code o} is {@code null}. 
     * @param o the reference to check for nullness
     * @param msg the message to be wrapped in the exception
     */
    public static void nullPtr (Object o, String msg){
        if (o == null)
            throw new NullPointerException(msg);
    }
    
    /**
     * Throws a {@link NullPointerException} if at least one object is {@code null},
     * with a message listing the parameter indexes of {@code null} elements. 
     * @param objs the references to check for nullness
     */
    public static void nullPtr (Object... objs){
        nullPtr ("Indices of null elements: ", objs);
    }
    
    public static void nullPtr (String msg, Object... objs){
        int i = 0;
        StringBuilder b = null;
        for (Object o : objs) {
            if (o == null) {
                if (b == null)
                    b = new StringBuilder().append(i);                    
                else
                    b.append(", ").append(i);
            }
            i++;
        }
        if (b != null)
            throw new NullPointerException(msg + b.toString());
    }
    
    /**
     * Throws a {@link NullPointerException} if at least one object is {@code null},
     * with a message listing the parameter indexes of {@code null} elements. 
     * @param objs the references to check for nullness
     */
    public static void nullPtr (Iterable<?> objs){
        int i = 0;
        StringBuilder b = null;
        for (Object o : objs) {
            if (o == null) {
                if (b == null)
                    b = new StringBuilder().append(i);                    
                else
                    b.append(", ").append(i);
            }
            i++;
        }
        if (b != null)
            throw new NullPointerException("Indices of null elements: " + b.toString());
    }
    
    /**
     * Throws an {@link IllegalArgumentException} with the given message if {@code condition}
     * evaluates to {@code true}. 
     * @param condition the boolean expression that needs to be {@code true} in order to have the exception thrown 
     * @param msg the message to be wrapped in the exception
     */
    public static void illArg (boolean condition, String msg) {
        if (condition)
            throw new IllegalArgumentException(msg);
    }
    
    /**
     * Throws an {@link IllegalArgumentException} with the given message if {@code obj}
     * evaluates to {@code true}. 
     * @param obj the object reference that needs to be {@code null} in order to have the exception thrown 
     * @param msg the message to be wrapped in the exception
     */
    public static void illArg (Object obj, String msg) {
        if (obj == null)
            throw new IllegalArgumentException(msg);
    }
    
    /**
     * Throws an {@link IllegalStateException} with the given message if {@code condition}
     * evaluates to {@code true}. 
     * @param condition the boolean expression that needs to be {@code true} in order to have the exception thrown 
     * @param msg the message to be wrapped in the exception
     */
    public static void illState (boolean condition, String msg) {
        if (condition)
            throw new IllegalStateException(msg);
    }
    
    /**
     * Throws an {@link IllegalThreadStateException} if not invoked on the Event Dispatching Thread.
     */
    public static void edt () {
        if (!SwingUtilities.isEventDispatchThread()) {
            throw new IllegalThreadStateException("This method must be invoked on the Event Dispatching Thread");
        }
    }
}
