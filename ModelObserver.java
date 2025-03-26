package Program6;

/**
 * Interface for objects that observe changes in the model.
 * This is part of the Observer pattern and is used to notify views or controllers
 * whenever the model updates.
 */
public interface ModelObserver {
    /**
     * Called when the observed model changes.
     */
    void modelChanged();
}