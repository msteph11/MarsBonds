package observers;

/**
 * Classes that implement this interface will observe SubjectApplication
 * objects.
 * @author Maria Stephenson
 */
public interface ApplicationObserver {

    /**
     * This method is called when the SubjectApplication notifies its observers
     */
    void update(String event);
}
