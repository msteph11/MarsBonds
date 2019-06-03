package model;

import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;

import java.util.ArrayList;

/**
 * Represents a molecule
 * @author Maria Stephenson
 */
public class Molecule extends Sphere{
    private final static int radius = 20;

    private Hybridization hybridization;
    private ArrayList<Bond> bonds;
    /**
     * Constructor: gives molecule default hybridization (sp3) and an empty list of bonds
     */
    public Molecule() {
        super(radius);
        hybridization = Hybridization.SP3;
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
}
