package observers;

/**
 * Classes that implement this interface will observe SubjectApplication
 * objects.
 * @author Maria Stephenson
 */
public interface ApplicationObserver {

    void update(String event);
}
