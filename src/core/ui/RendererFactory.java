package core.ui;

import javax.swing.ListCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * Distributes renderers for different Swing components based on requested class and flavor.
 * @author Timmos
 */
public interface RendererFactory {

    /**
     * Gets a renderer for list-based Swing components such as a JComboBox.
     * @param id identifies what renderable entity is to be rendered
     * @param flavor identifies how the renderable entity is to be rendered
     * @return a list cell renderer
     */
    public ListCellRenderer getListCellRenderer(RenderKey id, RenderFlavor flavor);

    /**
     * Gets a cell renderer for Swing tables.
     * @param id identifies what renderable entity is to be rendered
     * @param flavor identifies how the renderable entity is to be rendered
     * @return a list cell renderer
     */
    public TableCellRenderer getTableCellRenderer(RenderKey id, RenderFlavor flavor);
}
