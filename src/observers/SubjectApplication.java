package observers;

import javafx.application.Application;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents an application that notifies/maintains a list of
 * observers
 * @author Maria Stephenson
 */
public abstract class SubjectApplication extends Application {

    private static CopyOnWriteArrayList<ApplicationObserver> observers;

    public SubjectApplication() {
        observers = new CopyOnWriteArrayList<>();
    }

    public static void addObserver(ApplicationObserver o) {
        if(!observers.contains(o)) {
            observers.add(o);
        }
    }

    protected static void notifyObservers(String event) {
        for (ApplicationObserver o : observers) {
            o.update(event);
        }
    }

}
