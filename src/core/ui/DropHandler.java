package core.ui;

import java.io.File;
import java.util.List;

/**
 * A structure deciding whether incoming drop data can be dropped globally, and handles the data when actually dropped.
 * <p>Note: more methods for different data flavors may be added in the future.
 * @author Timmos
 */
public interface DropHandler {

    /**
     * Polls this drop handler whether an incoming drag can be accepted.
     * @param payload the incoming drag data
     * @return {@code true} when accepted, {@code false} otherwise
     */
    public boolean isFileDragAccepted (List<File> payload);

    /**
     * Handles the dropped data.
     * @param payload the incoming drag data
     */
    public void onFileDrop (List<File> payload);
}
