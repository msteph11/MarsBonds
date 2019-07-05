package model.model_elements;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import model.Atom;
import model.Bond;
import ui.MarsBonds;

/**
 * This controls an atom's bonds by allowing bonds to be added to it when the B key is pressed
 * @author Maria Stephenson
 */
public class BondController {

    private static final int BOND_OFFSET = 5;
    private static final int ATOM_OFFSET = 40;

    private Atom atom;
    private int bonds;

    // the pivots for the rotations that will be applied to a new atom and bond
    private double atomPivotX = 0;
    private double atomPivotY = 2 * Atom.RADIUS + Bond.BOND_LENGTH;
    private double atomPivotZ = 0;
    private double bondPivotX = 0;
    private double bondPivotY = Atom.RADIUS + Bond.BOND_LENGTH/2;
    private double bondPivotZ = 0;

    /**
     * Constructor
     * Makes a new BondController for given atom with either no bonds to control
     * (if it is the initial atom) or one bond to control (if it is not the initial atom)
     * @param atom The atom whose bonds this BondController controls
     * @param initial Whether the atom is the initial one or not
     */
    public BondController(Atom atom, boolean initial) {
        bonds = initial? 0 : 1;
        this.atom = atom;
    }

    /**
     * If the atom has less then the max number of bonds its hybridization allows,
     * adds another bond to the atom (if hybridization is sp3 - other hybridizations
     * are yet to be implemented)
     */
    public void addBond() {
        Hybridization atomHybrid = atom.getHybridization();
        if (atomHybrid.equals(Hybridization.SP) && bonds < Hybridization.SP.getMaxBonds())
            System.out.println("To be implemented");
        else if (atomHybrid.equals(Hybridization.SP2) && bonds < Hybridization.SP2.getMaxBonds())
            System.out.println("To be implemented");
        else if (atomHybrid.equals(Hybridization.SP3) && bonds < Hybridization.SP3.getMaxBonds())
            addBondSP3();
        else if (atomHybrid.equals(Hybridization.SP3D) && bonds < Hybridization.SP3D.getMaxBonds())
                  System.out.println("To be implemented");
        else if (atomHybrid.equals(Hybridization.SP3D2) && bonds < Hybridization.SP3D2.getMaxBonds())
            System.out.println("To be implemented");
    }

    /**
     * Adds a bond to an atom with sp3 hybridization
     */
    private void addBondSP3() {

        // sets up the new atom and bond above the bond controller's atom, in the vertical position
        Atom newAtom = new Atom();
        Bond bond = new Bond();
        newAtom.getTransforms().addAll(atom.getTransforms());
        bond.getTransforms().addAll(atom.getTransforms());
        bond.getTransforms().add(new Translate(0, -(Bond.BOND_LENGTH + BOND_OFFSET), 0));
        newAtom.getTransforms().add(new Translate(0, -(Bond.BOND_LENGTH + ATOM_OFFSET), 0));

        if (bonds > 0 && atom.isInitial()) {
            add3MoreBondsSP3InitialAtom(newAtom, bond);
        } else if (bonds > 0) {
            add3MoreBondsSP3(newAtom, bond);
        }

        Group group = new Group();
        group.getChildren().add(bond);
        group.getChildren().add(newAtom);
        addBondToScene(group);
    }

    private void addBondToScene(Group group) {
        bonds++;
        MarsBonds.addToScene(group);
    }

    /**
     * Adds one of the 3 bonds yet to be added to the initial atom in the screen, if
     * that atom has SP3 hybridization, and a bond already
     */
    private void add3MoreBondsSP3InitialAtom(Atom newAtom, Bond bond) {
        Rotate atomRotateZ = new Rotate(-109.5, atomPivotX, atomPivotY, atomPivotZ, Rotate.Z_AXIS);
        Rotate atomRotateY = new Rotate(-109.5 * (bonds - 1), atomPivotX, atomPivotY, atomPivotZ, Rotate.Y_AXIS);
        Rotate bondRotateZ = new Rotate(-109.5, bondPivotX, bondPivotY, bondPivotZ, Rotate.Z_AXIS);
        Rotate bondRotateY = new Rotate(-109.5 * (bonds - 1), bondPivotX, bondPivotY, bondPivotZ, Rotate.Y_AXIS);
        newAtom.getTransforms().addAll(atomRotateY, atomRotateZ);
        bond.getTransforms().addAll(bondRotateY, bondRotateZ);
    }

    /**
     * Adds one of the 3 bonds yet to be added to an atom in the screen, if
     * that atom has SP3 hybridization, and a bond already
     */
    private void add3MoreBondsSP3(Atom newAtom, Bond bond) {
        Rotate atomRotateZ = new Rotate(-70.5, atomPivotX, atomPivotY, atomPivotZ, Rotate.Z_AXIS);
        Rotate atomRotateX = new Rotate(bonds > 1? -109.5 : 0, atomPivotX, atomPivotY, atomPivotZ, Rotate.X_AXIS);
        Rotate bondRotateZ = new Rotate(-70.5, bondPivotX, bondPivotY, bondPivotZ, Rotate.Z_AXIS);
        Rotate bondRotateX = new Rotate(bonds > 1? -109.5 : 0, bondPivotX, bondPivotY, bondPivotZ, Rotate.X_AXIS);
        newAtom.getTransforms().addAll(atomRotateZ,  atomRotateX);
        bond.getTransforms().addAll(bondRotateZ,  bondRotateX);

        // adding the last bond requires more rotations
        if (bonds > 2) {
            Rotate atomRotateY = new Rotate( 109.5, atomPivotX, atomPivotY, atomPivotZ, Rotate.Z_AXIS);
            Rotate atomRotateX2 = new Rotate(-23.725, atomPivotX, atomPivotY, atomPivotZ, Rotate.X_AXIS);
            Rotate bondRotateY = new Rotate(109.5, bondPivotX, bondPivotY, bondPivotZ, Rotate.Z_AXIS);
            Rotate bondRotateX2 = new Rotate(-23.725, bondPivotX, bondPivotY, bondPivotZ,  Rotate.X_AXIS);
            newAtom.getTransforms().addAll(atomRotateY, atomRotateX2);
            bond.getTransforms().addAll(bondRotateY, bondRotateX2);
        }
    }
}
