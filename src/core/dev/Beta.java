package core.dev;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Marks a class, method or field as volatile - it's in beta and may still change, be removed or redefined.
 * @author Timmos
 */
@Retention(value = RetentionPolicy.SOURCE)
@Documented
public @interface Beta {
    
    /**
     * Explains why this structure is in beta.
     * @return an explanation
     */
    public String why ();
}
