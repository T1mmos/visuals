package gent.timdemey.visuals.core.ui;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.SwingUtilities;

import com.alee.laf.rootpane.WebFrame;

import gent.timdemey.visuals.core.log.msg.ErrorMsg;
import gent.timdemey.visuals.core.util.Log;
import gent.timdemey.visuals.core.util.Unchecked;

/**
 * Internal drop handling class, polling the {@link DropHandler} and directing the glass pane.
 * <p>This class aims to hide away Java's Drag&Drop API and calls the plugin's implementation
 * of the minimalistic Drag&Drop API interfaces provided by the framework.
 * @author Timmos
 */
class InternalDropHelper {

    private final InternalGlass glass;
    private final DropHandler plugin;

    private boolean dragging = false;
    private boolean dragGlass = false;
    private boolean dragFrame = false;

    private InternalDropHelper(WebFrame frame, InternalGlass glass, DropHandler plugin) {
        this.glass = glass;
        this.plugin = plugin;

        frame.setDropTarget(new DropTarget(frame, new FrameListener()));
        glass.setDropTarget(new DropTarget(glass, new GlassListener()));
        frame.setGlassPane(glass);
    }

    private static InternalDropHelper helper = null;

    /**
     * Installs the global drag&drop system.
     * @param frame the window
     * @param animation the animation triggered when the handler indicates a global drag
     * @param handler the system that decides when a drag is global, and handles actual drops
     */
    static void install(WebFrame frame, DropAnimation animation, DropHandler handler) {
        Unchecked.illState(helper != null, "The Drag&Drop system can be installed only once");

        InternalGlass glass = new InternalGlass(frame, animation);
        helper = new InternalDropHelper(frame, glass, handler);
    }

    private class Check implements Runnable {

        @Override
        public void run() {
            boolean showDrag = dragGlass || dragFrame;
            if (dragging == showDrag) {
                return; // nothing to do
            }
            dragging = showDrag;
            glass.setVisible(showDrag);
            glass.getDropTarget().setActive(showDrag);
        }
    }

    // TODO try to clean up the code duplication, this will become a mess.
    private static Object check(DropTargetDragEvent dtde) {
        List<DataFlavor> flavors = dtde.getCurrentDataFlavorsAsList();
        if (!flavors.contains(DataFlavor.javaFileListFlavor)) {
            return null; // only files on this point
        }
        Object obj;
        try {
            obj = dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
        } catch (UnsupportedFlavorException ex) {
            Log.error(ErrorMsg.DND_UNEXPECTED_FLAVOR_FILELISTS);
            Log.error(ex);
            return null;
        } catch (IOException ex) {
            Log.error(ex);
            return null;
        }
        return obj;
    }

    private static Object check(DropTargetDropEvent dtde) {
        List<DataFlavor> flavors = dtde.getCurrentDataFlavorsAsList();
        if (!flavors.contains(DataFlavor.javaFileListFlavor)) {
            return null; // only files on this point
        }
        Object obj;
        dtde.acceptDrop(DnDConstants.ACTION_COPY);
        try {
            obj = dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
        } catch (UnsupportedFlavorException ex) {
            Log.error(ErrorMsg.DND_UNEXPECTED_FLAVOR_FILELISTS);
            Log.error(ex);
            return null;
        } catch (IOException ex) {
            Log.error(ex);
            return null;
        }
        return obj;
    }

    private boolean isGlobalDrag (DropTargetDragEvent dtde) {
        Object obj = check(dtde);
        if (obj == null) {
            return false;
        }

        List<File> files;
        try {
            @SuppressWarnings("unchecked")
            List<File> payload = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
            files = payload;
        } catch (UnsupportedFlavorException ex) {
            Log.error(ErrorMsg.DND_UNEXPECTED_FLAVOR_FILELISTS);
            return false;
        } catch (IOException ex) {
            Log.error(ex);
            return false;
        }

        boolean accepted;
        try {
            accepted = plugin.isFileDragAccepted(files);
        } catch (RuntimeException rte) {
            Log.bug(rte);
            accepted = false;
        }
        if (accepted) {
            dtde.acceptDrag(DnDConstants.ACTION_COPY);
        } else {
            dtde.rejectDrag();
        }
        return accepted;
    }

    private void onDrop(DropTargetDropEvent dtde) {
        Object obj = check(dtde);
        if (obj == null) {
            Log.error(ErrorMsg.DND_UNEXPECTED_NULL);
            return;
        }
        List<File> files;
        try {
            @SuppressWarnings("unchecked")
            List<File> payload = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
            files = payload;
        } catch (UnsupportedFlavorException ex) {
            // TODO Auto-generated catch block
            Log.error(ErrorMsg.DND_UNEXPECTED_FLAVOR_FILELISTS);
            return;
        } catch (IOException ex) {
            Log.error(ex);
            return;
        }
        plugin.onFileDrop(files);
    }

    private class FrameListener extends DropTargetAdapter {

        @Override
        public void dragEnter(DropTargetDragEvent dtde) {
            dragFrame = isGlobalDrag(dtde);
            SwingUtilities.invokeLater(new Check());
        }

        @Override
        public void dragExit(DropTargetEvent dte) {
            dragFrame = false;
            SwingUtilities.invokeLater(new Check());
        }

        @Override
        public void drop(DropTargetDropEvent dtde) {
            onDrop(dtde);
            dragFrame = false;
            SwingUtilities.invokeLater(new Check());
        }
    }

    private class GlassListener extends DropTargetAdapter {

        @Override
        public void dragEnter(DropTargetDragEvent dtde) {
            dragGlass = isGlobalDrag(dtde);
            SwingUtilities.invokeLater(new Check());
        }

        @Override
        public void dragExit(DropTargetEvent dte) {
            dragGlass = false;
            SwingUtilities.invokeLater(new Check());
        }

        @Override
        public void drop(DropTargetDropEvent dtde) {
            onDrop(dtde);
            dragGlass = false;
            SwingUtilities.invokeLater(new Check());
        }
    }
}
