package demo.loading;

import core.kernel.Loader;
import core.kernel.LoaderKey;

/**
 * Identifies demo-specific {@link Loader}s.
 * @author Timmos
 */
public enum DemoLoaderKey implements LoaderKey {
    /** Identifies the menu loader. */
    MENU,
    /** An init loader, setting some details. */
    SMALL_THINGS,
    /** Loads the UI components in the content panel. */
    CONTENT_PANEL,
    /** Loads the status bar. */
    STATUSBAR,
    /** Loads the toolbar. */
    TOOLBAR
}
