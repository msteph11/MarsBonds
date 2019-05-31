package model;

import java.awt.*;

/**
 * Represents a bond between two molecules
 * @author Maria Stephenson
 */
public class Bond {
    private final static int BOND_LENGTH = 2;

    private final Molecule moleculeOne;
    private final Molecule moleculeTwo;
    private Color color;

    /**
     * Construcotr: creates a bond between m1 and m2
     * @param m1 molecule participating in the bond
     * @param m2 molecule participating in the bond
     */
    public Bond(Molecule m1, Molecule m2) {
        moleculeOne = m1;
        moleculeTwo = m2;
    }
}
