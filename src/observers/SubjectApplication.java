package observers;

import javafx.application.Application;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents an application that notifies/maintains a list of
 * ApplicationObserver objects.
 * @author Maria Stephenson
 */
public abstract class SubjectApplication extends Application {

    private static CopyOnWriteArrayList<ApplicationObserver> observers;

    /**
     * Constructor
     * Makes a new SubjectApplication with no observers
     */
    public SubjectApplication() {
        observers = new CopyOnWriteArrayList<>();
    }

    /**
     * Adds an ApplicationObserver to the list of observers (if it's not
     * already there)
     * @param o ApplicationObserver to be added
     */
    public static void addObserver(ApplicationObserver o) {
        if(!observers.contains(o)) {
            observers.add(o);
        }
    }

    /**
     * Notifies observers that something has happened (either C key
     * is pressed, B key is pressed, mouse is clicked)
     */
    protected static void notifyObservers(String event) {
        for (ApplicationObserver o : observers) {
            o.update(event);
        }
    }

}
