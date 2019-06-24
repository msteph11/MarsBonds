package model;

import javafx.scene.shape.Cylinder;

/**
 * Represents a bond between two atoms
 * @author Maria Stephenson
 */
public class Bond extends Cylinder {
    public final static int BOND_LENGTH = 30;
    public final static int RADIUS = 3;

    public Bond() {
        super(RADIUS, BOND_LENGTH);
    }
}
