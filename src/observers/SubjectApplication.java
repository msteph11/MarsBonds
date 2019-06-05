package observers;

import javafx.application.Application;

import java.util.ArrayList;

/**
 * Represents an application that notifies/maintains a list of
 * ApplicationObserver objects.
 * @author Maria Stephenson
 */
public abstract class SubjectApplication extends Application {

    private ArrayList<ApplicationObserver> observers;

    /**
     * Constructor
     * Makes a new SubjectApplication with no observers
     */
    public SubjectApplication() {
        observers = new ArrayList<>();
    }

    /**
     * Adds an ApplicationObserver to the list of observers (if it's not
     * already there)
     * @param o ApplicationObserver to be added
     */
    public void addObserver(ApplicationObserver o) {
        if(!observers.contains(o)) {
            observers.add(o);
        }
    }

    /**
     * Notifies observers that something has happened (as of now,
     * this method is only called when the C key is pressed, but this may
     * change in future versions)
     */
    public void notifyObservers() {
        for (ApplicationObserver o : observers) {
            o.update();
        }
    }

}
