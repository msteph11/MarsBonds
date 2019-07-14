package ui;

import javafx.scene.Group;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

/**
 * A group that includes methods which allow it to be rotated
 * @author Maria Stephenson
 */
public class RotateGroup extends Group {
    private Transform currTransform;

    /**
     * Constructor
     * Creates a new group with default Rotate transform
     */
    public RotateGroup() {
        currTransform = new Rotate();
    }

    /**
     * Rotates the group along the x-axis according to the given angle
     * @param angle the amount of degrees the group is rotated
     */
    public void rotateInX(int angle) {
        try {
            Rotate newRotation = new Rotate(angle, currTransform.inverseDeltaTransform(Rotate.X_AXIS));
            rotateGroup(newRotation);
        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rotates the group along the y-axis according to the given angle
     * @param angle the amount of degrees the group is rotated
     */
    public void rotateInY(int angle) {
        try {
            Rotate newRotation = new Rotate(angle, currTransform.inverseDeltaTransform(Rotate.Y_AXIS));
            rotateGroup(newRotation);
        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rotates the group along the z-axis according to the given angle
     * @param angle the amount of degrees the group is rotated
     */
    public void rotateInZ(int angle) {
        try {
            Rotate newRotation = new Rotate(angle, currTransform.inverseDeltaTransform(Rotate.Z_AXIS));
            rotateGroup(newRotation);
        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    /**
     * Applies the given rotation to the group
     */
    private void rotateGroup(Rotate rotation) {
        currTransform = currTransform.createConcatenation(rotation);
        getTransforms().clear();
        getTransforms().addAll(currTransform);
    }
}
