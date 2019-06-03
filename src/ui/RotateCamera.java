package ui;

import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

/**
 * A PerspectiveCamera that includes methods which allow it to be rotated
 * @author Maria Stephenson
 */
public class RotateCamera extends PerspectiveCamera {
    Transform currTransform;

    /**
     * Constructor
     * Creates a new camera with default Rotate transform
     */
    public RotateCamera() {
        currTransform = new Rotate();
    }

    /**
     * Rotates the camera along the x-axis according to the given angle
     * @param angle the amount of degrees the camera is rotated
     */
    public void rotateInX(int angle) {
       Rotate newRotation = new Rotate(angle, Rotate.X_AXIS);
       rotateCam(newRotation);
    }

    /**
     * Rotates the camera along the y-axis according to the given angle
     * @param angle the amount of degrees the camera is rotated
     */
    public void rotateInY(int angle) {
        Rotate newRotation = new Rotate(angle, Rotate.Y_AXIS);
        rotateCam(newRotation);
    }

    /**
     * Applies both the currTransform and given rotation to the camera
     */
    private void rotateCam(Rotate rotation) {
        currTransform = currTransform.createConcatenation(rotation);
        getTransforms().clear();
        getTransforms().addAll(currTransform);
    }
}
