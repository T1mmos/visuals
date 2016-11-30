package gent.timdemey.visuals.core.kernel;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import net.miginfocom.swing.MigLayout;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;

/**
 * An implementation of {@link LoadErrorReporter} where a user is informed via the GUI.
 * @author Timmos
 */
public class GuiErrorReporter implements LoadErrorReporter {

    private static void onLoadingFailedGui (LoadingFailedException ex) {
        // TODO this method could be cleaner.
        StringWriter writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer));
        final String stacktrace = writer.toString();

        WebPanel pnl_content = new WebPanel (new MigLayout());
        WebLabel lab_msg = new WebLabel("The application failed to load: " + ex.getMessage());
        WebButton but_ok = new WebButton(new AbstractAction("Exit") {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(ExitCodes.LOADING_ERROR.getCode());
            }});
        WebButton but_cpy = new WebButton(new AbstractAction("Copy to Clipboard") {

            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(stacktrace);
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
            }
        });
        WebTextArea stack = new WebTextArea(stacktrace, 0, 120);
        stack.setFont(Font.decode("Courier New"));
        WebScrollPane scroll = new WebScrollPane(stack);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        pnl_content.add(lab_msg, "growx, wrap");
        pnl_content.add(scroll, "grow, wrap");
        pnl_content.add(but_cpy, "span, split 2, center, sg buts");
        pnl_content.add(but_ok, "sg buts");

        JFrame frame = new JFrame();
        frame.setTitle("Application loading error");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(pnl_content);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }

    @Override
    public void report(final LoadingFailedException ex) {
        ex.printStackTrace();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GuiErrorReporter.onLoadingFailedGui(ex);
            }
        });
    }

}
