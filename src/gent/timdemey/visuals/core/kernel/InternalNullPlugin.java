package gent.timdemey.visuals.core.kernel;

import java.awt.Font;
import java.awt.Image;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.alee.extended.statusbar.WebStatusBar;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebTextArea;
import com.alee.laf.toolbar.WebToolBar;

import gent.timdemey.visuals.core.cfg.Configuration;
import gent.timdemey.visuals.core.cfg.MetaConfigKey;
import gent.timdemey.visuals.core.log.msg.WarnMsg;
import gent.timdemey.visuals.core.res.DefaultResourceFlag;
import gent.timdemey.visuals.core.ui.ChoiceDialog;
import gent.timdemey.visuals.core.ui.DefaultIconKey;
import gent.timdemey.visuals.core.ui.DialogButton;
import gent.timdemey.visuals.core.ui.DialogInput;
import gent.timdemey.visuals.core.ui.DropAnimation;
import gent.timdemey.visuals.core.ui.DropHandler;
import gent.timdemey.visuals.core.ui.GuiSystem;
import gent.timdemey.visuals.core.ui.IconKey;
import gent.timdemey.visuals.core.ui.MessageType;
import gent.timdemey.visuals.core.util.Gui;
import gent.timdemey.visuals.core.util.Log;
import gent.timdemey.visuals.core.util.Various;
import net.miginfocom.swing.MigLayout;

/**
 * A plugin that does nothing but show an information message, telling that a real plugin was not set.
 * This class is instantiated only by the {@link InternalMainLoader core loader}, when a plugin
 * could not be loaded: either no plugin class was set via the program arguments or the meta configuration,
 * or it was set but referred to an unknown class, or a class not implementing the {@link Plugin} interface.
 * @author Timmos
 */
class InternalNullPlugin implements Plugin {

    private final String rawPluginName;
    private final String reason;

    /**
     * Creates a new null plugin.
     * @param rawPluginName the raw string referring to the plugin that could not be loaded
     * @param msg the reason why the core failed to use a plugin based on the the plugin name
     */
    InternalNullPlugin (String rawPluginName, String msg){
        this.rawPluginName = rawPluginName;
        this.reason = msg;
    }

    @Override
    public Splash getSplash() {
        return null;
    }

    @Override
    public NullApplicationBuilder newAppBuilder() {
        return new NullApplicationBuilder();
    }

    private static class NullApplicationBuilder extends DefaultAppBuilder {

        @Override
        public DefaultApp build() {
            return DefaultApp.dummy();
        }
    }

    private static class NullBootBuilder extends DefaultBootBuilder {

        public NullBootBuilder(InternalNullPlugin plugin) {
            super(plugin, null, null);
        }

        @Override
        public DefaultBoot build() {
            return DefaultBoot.dummy();
        }
    }

    @Override
    public DefaultInitBuilder newInitBuilder() {
        return new DefaultInitBuilder();
    }

    @Override
    public DefaultGuiBuilder newGuiBuilder() {
        return new DefaultGuiBuilder() {

            @Override
            public DefaultGui build() {
                return DefaultGui.dummy(new NullGuiSystem());
            }
        };
    }

    @Override
    public void putBootLoaders(Map<LoaderKey, BootLoader> loaders) {
    }

    @Override
    public DefaultBootBuilder newBootBuilder(Configuration meta, Configuration normal) {
        return new NullBootBuilder(this);
    }

    @Override
    public void putAppLoaders(Map<LoaderKey, AppLoader> loaders) {
        // do nothing
    }

    @Override
    public void putGuiLoaders(Map<LoaderKey, GuiLoader> loaders) {
        // do nothing
    }

    @Override
    public void putInitLoaders(Map<LoaderKey, InitLoader> loaders) {
        // do nothing
    }

    private class NullGuiSystem implements GuiSystem {

        @Override
        public void start() {
            String plgStr = MetaConfigKey.PLUGIN.getExternalIdentifier() + "=" +
                            (rawPluginName == null
                            ? "<none>" : rawPluginName);
            final String message = "Hello world!\n\n"
                            + "This is NullPlugin. It does nothing but show you this message, "
                            + "to inform you that no suitable plugin was provided to the application "
                            + "framework. Therefore, NullPlugin was chosen, resulting in this "
                            + "message.\n\n"
                            + "The plugin class was defined as follows:\n"
                            + plgStr + "\n\n"
                            +  "The application failed to load because " + reason + ".\n\n"
                            + "To get you started: feed a Plugin to the application framework, "
                            + "by simply setting the key "+MetaConfigKey.PLUGIN.getExternalIdentifier()+": either via the application's "
                            + "command line arguments, or via the meta configuration file. It should "
                            + "refer to a class implementing the Plugin interface (as NullPlugin does).\n\n"
                            + "Good luck!";
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {

                    WebTextArea text = Various.createMultiLine(message);
                    text.setColumns(100);
                    text.setFont(Font.decode("Consolas"));
                    WebPanel content = new WebPanel(new MigLayout(""));
                    content.add(text, "push, growx, wrap");

                    final WebFrame frame = new WebFrame("Plugin not found");
                    frame.setContentPane(content);
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                    IconKey icon = DefaultIconKey.ERROR;
                    try {
                        String png = icon.getExternalIdentifier();
                        String path = "img/" + png;
                        InputStream is = DefaultResourceFlag.class.getResourceAsStream(path);
                        Image img = ImageIO.read(is);
                        frame.setIconImage(img);
                    } catch (Throwable t) {
                        Log.warn(WarnMsg.RESOURCE_LOAD_FAIL, icon.getExternalIdentifier());
                    }
                    frame.pack();
                    frame.setResizable(false);

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            frame.pack();
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);
                        }
                    });
                }
            });
        }

        @Override
        public void setMainFrameTitle(String locKey) {
            // do nothing
        }

        @Override
        public void setMainFrameIcon(IconKey iconKey) {
            // do nothing
        }

        @Override
        public void setMenuBar(WebMenuBar menubar) {
            // do nothing
        }

        @Override
        public void setContentPane(WebPanel content) {
            // do nothing
        }

        @Override
        public ChoiceDialog<Void> createMessage(String msg) {
            return null;
        }

        @Override
        public ChoiceDialog<Void> createMessage(String msg, MessageType type) {
            return null;
        }

        @Override
        public ChoiceDialog<Void> createMessage(String msg, String title, IconKey iconKey) {
            return null;
        }

        @Override
        public ChoiceDialog<Void> createYesNoQuestion(String question, String title) {
            return null;
        }

        @Override
        public ChoiceDialog<Void> createYesNoCancelQuestion(String question, String title) {
            return null;
        }

        @Override
        public ChoiceDialog<String> createInputQuestion(String question, String title) {
            return null;
        }

        @Override
        public ChoiceDialog<String> createInputQuestion(String question, String title, IconKey iconKey) {
            return null;
        }

        @Override
        public void setDropPlugin(DropHandler handler, DropAnimation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void setToolBar(WebToolBar toolBar) {
            // TODO Auto-generated method stub

        }

        @Override
        public ChoiceDialog<Void> createCustomQuestion(String question, String title, IconKey iconKey,
                        Set<? extends DialogButton> buttons) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public <T> ChoiceDialog<T> createCustomDialog(DialogInput<T> input, String title, IconKey iconKey,
                        Set<? extends DialogButton> buttons) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void showNotification(String msg, MessageType type) {
            // TODO Auto-generated method stub

        }

        @Override
        public void setStatusBar(WebStatusBar content) {
            // TODO Auto-generated method stub

        }

        @Override
        public ChoiceDialog<Void> createYesNoQuestion(String question, String title, IconKey iconKey) {
            // TODO Auto-generated method stub
            return null;
        }
    }

    @Override
    public void onLoadingSucceeded() {
        Gui.getGuiSystem().start();
    }
}
