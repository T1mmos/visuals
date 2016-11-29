package core.ui;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ListCellRenderer;
import javax.swing.table.TableCellRenderer;

import core.util.Pair;

/**
 * The default {@link RendererFactory}.
 * @author Timmos
 */
public class DefaultRendererFactory implements RendererFactory {

    private final static class InternalKey extends Pair<RenderKey, RenderFlavor> {

        public InternalKey(RenderKey first, RenderFlavor second) {
            super(first, second);
        }
    }

    /**
     * Builds a {@link DefaultRendererFactory}.
     * @author Timmos
     */
    public static final class Builder implements RendererFactoryBuilder {

        private final Map<InternalKey, ListCellRenderer>  listRenderers;
        private final Map<InternalKey, TableCellRenderer> tableRenderers;

        /**
         * Creates a new builder.
         */
        public Builder() {
            this.listRenderers = new HashMap<InternalKey, ListCellRenderer>();
            this.tableRenderers = new HashMap<InternalKey, TableCellRenderer>();
        }

        @Override
        public void add(RenderKey id, RenderFlavor flavor, ListCellRenderer renderer) {
            add(id, flavor, "ListCellRenderer", listRenderers, renderer);
        }

        @Override
        public void add(RenderKey id, RenderFlavor flavor, TableCellRenderer renderer) {
            add(id, flavor, "TableCellRenderer", tableRenderers, renderer);
        }

        private static <T> void add(RenderKey id, RenderFlavor flavor, String what,
                        Map<InternalKey, T> renderers,
                        T renderer) {
            InternalKey key = new InternalKey(id, flavor);
            if (renderers.containsKey(key)) {
                throw new IllegalStateException(what + " already added for class and flavor: " + id + ", " + flavor);
            }
            renderers.put(key, renderer);
        }

        @Override
        public DefaultRendererFactory build() {
            return new DefaultRendererFactory(listRenderers, tableRenderers);
        }
    }

    private final Map<InternalKey, ListCellRenderer>  listRenderers;
    private final Map<InternalKey, TableCellRenderer> tableRenderers;

    /**
     * @param listRenderers the list cell renderers
     * @param tableRenderers the table cell renderers
     */
    private DefaultRendererFactory(
                    Map<InternalKey, ListCellRenderer> listRenderers,
                    Map<InternalKey, TableCellRenderer> tableRenderers) {
        this.listRenderers = Collections.unmodifiableMap(listRenderers);
        this.tableRenderers = Collections.unmodifiableMap(tableRenderers);
    }

    private static <T> T getRenderer(Map<InternalKey, ?> anyRenderers, String what, RenderKey id,
                    RenderFlavor flavor) {
        Object anyRenderer = anyRenderers.get(new InternalKey(id, flavor));
        if (anyRenderer == null) {
            throw new IllegalArgumentException("No " + what + " installed for id=" + id + " and flavor=" + flavor);
        }

        @SuppressWarnings("unchecked")
        T conv = (T) anyRenderer;
        return conv;
    }

    @Override
    public ListCellRenderer getListCellRenderer(RenderKey id, RenderFlavor flavor) {
        return getRenderer(listRenderers, "ListCellRenderer", id, flavor);
    }

    @Override
    public TableCellRenderer getTableCellRenderer(RenderKey id, RenderFlavor flavor) {
        return getRenderer(tableRenderers, "TableCellRenderer", id, flavor);
    }
}
