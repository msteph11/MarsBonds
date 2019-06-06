package model;

import javafx.scene.shape.Cylinder;

/**
 * Represents a bond between two molecules
 * @author Maria Stephenson
 */
public class Bond extends Cylinder {
    public final static int BOND_LENGTH = 30;
    public final static int RADIUS = 3;

    private final Atom atomOne;
    private final Atom atomTwo;

    /**
     * Construcotr: creates a bond between m1 and m2
     * @param m1 molecule participating in the bond
     * @param m2 molecule participating in the bond
     */
    public Bond(Atom m1, Atom m2) {
        super(RADIUS, BOND_LENGTH);
        atomOne = m1;
        atomTwo = m2;
    }
}
