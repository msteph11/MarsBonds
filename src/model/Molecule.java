package model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a molecule
 * @author Maria Stephenson
 */
public class Molecule {
    private Hybridization hybridization;
    private ArrayList<Bond> bonds;
    private Color color;

    /**
     * Constructor: gives molecule default hybridization (sp3), default colour
     * (red), and an empty list of bonds
     */
    public Molecule() {
        hybridization = Hybridization.SP3;
        color = Color.red;
        bonds = new ArrayList<>();
    }

    /**
     * Getter
     * @return Molecule's hybridization
     */
    public Hybridization getHybridization() {
        return hybridization;
    }
    /**
     * Getter
     * @return Molecule's bonds
     */
    public ArrayList<Bond> getBonds() {
        return bonds;
    }

    /**
     * Getter
     * @return Molecule's color
     */
    public Color getColor() {
        return color;
    }
}
