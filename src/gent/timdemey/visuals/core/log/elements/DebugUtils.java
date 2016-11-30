package gent.timdemey.visuals.core.log.elements;

import java.util.ArrayList;
import java.util.List;

import gent.timdemey.visuals.core.log.Debuggable;
import gent.timdemey.visuals.core.log.printers.StringDebugPrinter;


public class DebugUtils {

    private static final List<Class<?>> EXCEPTIONS = new ArrayList<>();

    static {
        EXCEPTIONS.add(Integer.class);
        EXCEPTIONS.add(Double.class);
        EXCEPTIONS.add(String.class);
    }

    /**
     * Returns an array with String representations of the given objects. The objects are checked whether they
     * implement the {@link Debuggable} interface and if so, their {@link Debuggable#getDebugInfo()} method is
     * used to extract information, which is converted to a String by using a {@link StringDebugPrinter}. If
     * an object does not implement the interface, its {@link #toString()} method is used.
     * @param args the objects to prettify
     * @return an array with length equal to the input array length, containing Strings representing the objects
     * in the input array, keeping the order
     */
    public static Object[] prettify(Object ... args) {
        Object[] prettier = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            Object obj = args[i];
            if (obj instanceof Debuggable) {
                StringDebugPrinter printer = new StringDebugPrinter();
                ((Debuggable) obj).getDebugInfo().accept(printer);
                prettier[i] = printer.print();
            } else if (obj == null) {
                prettier[i] = "<null>";
            } else if (EXCEPTIONS.contains(obj.getClass())) {
                prettier[i] = obj;
            } else {
                prettier[i] = obj.toString();
            }
        }
        return prettier;
    }

}
