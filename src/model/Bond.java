package model;

import javafx.scene.shape.Cylinder;

/**
 * Represents a bond between two molecules
 * @author Maria Stephenson
 */
public class Bond extends Cylinder {
    public final static int BOND_LENGTH = 30;
    public final static int RADIUS = 3;

    /**
     * Constructor: creates a bond between two atoms
     * a bond is a cylinder with radius RADIUS and height
     * BOND_LENGTH
     */
    public Bond() {
        super(RADIUS, BOND_LENGTH);
    }
}
