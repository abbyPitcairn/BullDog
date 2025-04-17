package Program8;

/**
 * Interface for objects that observe changes in the model.
 * This is part of the Observer pattern and is used to notify views or controllers
 * whenever the model updates.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version March 26, 2025
 */
public interface ModelObserver {
    /**
     * Called when the observed model changes.
     */
    void modelChanged();
}