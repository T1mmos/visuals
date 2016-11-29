package core.serial;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

final class ConverterNode<O> {

    private final ConverterNode<O>       parent;
    private final List<ConverterNode<O>> children;
    private final Class<?>               inCls;
    private final Class<O>               outCls;

    private Converter<?, O>              converter = null;

    private ConverterNode(ConverterNode<O> parent, Class<?> inCls, Class<O> outCls) {
        this.parent = parent;
        this.children = new ArrayList<ConverterNode<O>>();
        this.inCls = inCls;
        this.outCls = outCls;
    }


    <T> void addChild(Class<T> cls, Converter<T, O> conv) {
        LinkedList<Class<?>> line = getHierarchy(cls);
        ensureTree(line);

        ConverterNode<O> child = new ConverterNode<O>(this, cls, outCls);
        child.converter = conv;
    }


    <I> Converter<I, O> getConverter(Class<I> clazz) {
        if (inCls == clazz) {
            @SuppressWarnings("unchecked")
            Converter<I, O> conv = (Converter<I, O>) this.converter;
            if (conv == null) {
                String msg = String.format("Class %s was not added to the converter tree", clazz.getSimpleName());
                throw new IllegalArgumentException(msg);
            }
            return conv;
        }
        for (ConverterNode<O> child : children) {
            if (child.inCls.isAssignableFrom(clazz)) {
                return child.getConverter(clazz);
            }
        }

        String msg = String.format("Class %s was not added to the converter tree", clazz.getSimpleName());
        throw new IllegalArgumentException(msg);
    }

    private void ensureTree(LinkedList<Class<?>> line) {
        Class<?> branchCls = line.removeLast();

        for (ConverterNode<O> child : children) {
            if (child.inCls == branchCls) {
                child.ensureTree(line);
                return;
            }
        }
        // no direct child for this line yet, create a path
        ConverterNode<O> child = new ConverterNode<O>(this, branchCls, outCls);
        children.add(child);
        child.ensureTree(line);
    }

    private LinkedList<Class<?>> getHierarchy(Class<?> subCls) {
        LinkedList<Class<?>> line = new LinkedList<Class<?>>();

        Class<?> next = subCls;
        do {
            line.add(next);
            next = next.getSuperclass();
        } while (next != this.inCls);

        return line;
    }

}

