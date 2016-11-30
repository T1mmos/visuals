package gent.timdemey.visuals.core.ui;

import javax.swing.ListCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * Builds a {@link RendererFactory} during application load.
 * @author Timmos
 */
public interface RendererFactoryBuilder {

    /**
     * Adds a {@link ListCellRenderer} to the builder. The combination of {@link RenderKey} and {@link RenderFlavor}
     * should
     * be unique.
     * @param id identifies for what data the renderer is designed for
     * @param flavor identifies how the data should be rendered (e.g. textual or visual, ...)
     * @param renderer the renderer
     * @throws IllegalStateException when the combination of is and flavor was added before
     */
    public void add(RenderKey id, RenderFlavor flavor, ListCellRenderer renderer);

    /**
     * Adds a {@link TableCellRenderer} to the builder. The combination of {@link RenderKey} and {@link RenderFlavor}
     * should be unique.
     * @param id identifies for what data the renderer is designed for
     * @param flavor identifies how the data should be rendered (e.g. textual or visual, ...)
     * @param renderer the renderer
     * @throws IllegalStateException when the combination of is and flavor was added before
     */
    public void add(RenderKey id, RenderFlavor flavor, TableCellRenderer renderer);

    /**
     * Instantiates an implementation of {@link RendererFactory} with the renderers set in this builder.
     * @return an instance of an implementation of {@link RendererFactory}
     */
    public RendererFactory build();
}
