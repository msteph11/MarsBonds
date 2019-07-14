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
    // the pivots for the rotations that will be applied to a new atom and bond
    private static final double Atom_Pivot_X = 0;
    private static final double ATOM_PIVOT_Y = 2 * Atom.RADIUS + Bond.BOND_LENGTH;
    private static final double ATOM_PIVOT_Z = 0;
    private static final double BOND_PIVOT_X = 0;
    private static final double BOND_PIVOT_Y = Atom.RADIUS + Bond.BOND_LENGTH/2;
    private static final double BOND_PIVOT_Z = 0;

    private Atom atom;
    private int bonds;


    /**
     * Constructor
     * Makes a new BondController for given atom with either no bonds to control
     * (if it is the initial atom) or one bond to control (if it is not the initial atom)
     * @param atom The atom whose bonds this BondController controls
     */
    public BondController(Atom atom) {
        bonds = atom.isInitial()? 0 : 1;
        this.atom = atom;
    }

    public int getNumberOfBonds() {
        return bonds;
    }

    /**
     * If the atom has less then the max number of bonds its hybridization allows,
     * adds another bond to the atom
     */
    public void addBond() {
        Hybridization atomHybrid = atom.getHybridization();
        if (atomHybrid.equals(Hybridization.SP) && bonds < Hybridization.SP.getMaxBonds())
            addBondSP();
        else if (atomHybrid.equals(Hybridization.SP2) && bonds < Hybridization.SP2.getMaxBonds())
            addBondSP2();
        else if (atomHybrid.equals(Hybridization.SP3) && bonds < Hybridization.SP3.getMaxBonds())
            addBondSP3();
        else if (atomHybrid.equals(Hybridization.SP3D) && bonds < Hybridization.SP3D.getMaxBonds())
            addBondsSP3D();
        else if (atomHybrid.equals(Hybridization.SP3D2) && bonds < Hybridization.SP3D2.getMaxBonds())
            addBondSP3D2();
    }

    /**
     * Adds a new bond to atom with sp hybridization (linear shape)
     */
    private void addBondSP() {
        Atom newAtom = new Atom();
        Bond bond = new Bond();
        addAtomInLine(newAtom, bond);
        if (bonds == 1) {
            Rotate atomRotateZ = new Rotate(180, Atom_Pivot_X, ATOM_PIVOT_Y, ATOM_PIVOT_Z, Rotate.Z_AXIS);
            Rotate bondRotateZ = new Rotate(180, BOND_PIVOT_X, BOND_PIVOT_Y, BOND_PIVOT_Z, Rotate.Z_AXIS);
            newAtom.getTransforms().add(atomRotateZ);
            bond.getTransforms().add(bondRotateZ);
        }
    }

    /**
     * Adds a new bond to atom with sp2 hybridization (trigonal planar shape)
     */
    private void addBondSP2() {
        Atom newAtom = new Atom();
        Bond bond = new Bond();
        addAtomInLine(newAtom, bond);
        if (bonds >= 2) {
            // NOTE: the reason we rotate 60 degrees and then -60 degrees if the atom isn't the initial, is because
            //       we place the second and third bond 180 degrees from the first, and all bonds must be 120 degrees apart
            Rotate atomRotateZ = new Rotate(atom.isInitial()? -120 * (bonds - 1) : 60 - 120 * (bonds - 2), Atom_Pivot_X, ATOM_PIVOT_Y, ATOM_PIVOT_Z, Rotate.Z_AXIS);
            Rotate bondRotateZ = new Rotate(atom.isInitial()? -120 * (bonds - 1) : 60 - 120 * (bonds - 2), BOND_PIVOT_X, BOND_PIVOT_Y, BOND_PIVOT_Z, Rotate.Z_AXIS);
            newAtom.getTransforms().add(atomRotateZ);
            bond.getTransforms().add(bondRotateZ);
        }
    }


    /**
     * Adds a bond to an atom with sp3 hybridization (tetrahedral shape)
     */
    private void addBondSP3() {
        Atom newAtom = new Atom();
        Bond bond = new Bond();
        addAtomInLine(newAtom, bond);
        if (bonds >= 2) {
            // NOTE: the reason we rotate -70.5 degrees in the Z axis if the atom is not the initial atom, is because
            //       the bond is placed 180 degrees from the first bond
            Rotate atomRotateZ = new Rotate(atom.isInitial()? -109.5 : -70.5, Atom_Pivot_X, ATOM_PIVOT_Y, ATOM_PIVOT_Z, Rotate.Z_AXIS);
            Rotate atomRotateY = new Rotate(-120 * (bonds - 2), Atom_Pivot_X, ATOM_PIVOT_Y, ATOM_PIVOT_Z, Rotate.Y_AXIS);
            Rotate bondRotateZ = new Rotate(atom.isInitial()? -109.5 : -70.5, BOND_PIVOT_X, BOND_PIVOT_Y, BOND_PIVOT_Z, Rotate.Z_AXIS);
            Rotate bondRotateY = new Rotate(-120 * (bonds - 2), BOND_PIVOT_X, BOND_PIVOT_Y, BOND_PIVOT_Z, Rotate.Y_AXIS);
            newAtom.getTransforms().addAll(atomRotateY, atomRotateZ);
            bond.getTransforms().addAll(bondRotateY, bondRotateZ);
        }
    }

    /**
     * Adds a bond to an atom with sp3d hybridization (trigonal bipyramdial shape)
     */
    private void addBondsSP3D() {
        Atom newAtom = new Atom();
        Bond bond = new Bond();
        addAtomInLine(newAtom, bond);
        if (bonds >= 2 && bonds <= 4) {
            Rotate atomRotateZ = new Rotate(90, Atom_Pivot_X, ATOM_PIVOT_Y, ATOM_PIVOT_Z, Rotate.Z_AXIS);
            Rotate atomRotateY = new Rotate(-120 * (bonds - 2), Atom_Pivot_X, ATOM_PIVOT_Y, ATOM_PIVOT_Z, Rotate.Y_AXIS);
            Rotate bondRotateZ = new Rotate(90, BOND_PIVOT_X, BOND_PIVOT_Y, BOND_PIVOT_Z, Rotate.Z_AXIS);
            Rotate bondRotateY = new Rotate(-120 * (bonds - 2), BOND_PIVOT_X, BOND_PIVOT_Y, BOND_PIVOT_Z, Rotate.Y_AXIS);
            newAtom.getTransforms().addAll(atomRotateY, atomRotateZ);
            bond.getTransforms().addAll(bondRotateY, bondRotateZ);
        } else if (bonds == 5 && atom.isInitial()) {
            Rotate atomRotateZ = new Rotate(180, Atom_Pivot_X, ATOM_PIVOT_Y, ATOM_PIVOT_Z, Rotate.Z_AXIS);
            Rotate bondRotateZ = new Rotate(180, BOND_PIVOT_X, BOND_PIVOT_Y, BOND_PIVOT_Z, Rotate.Z_AXIS);
            newAtom.getTransforms().add(atomRotateZ);
            bond.getTransforms().add(bondRotateZ);
        }
    }

    /**
     * Adds a bond to an atom with sp3d2 hybridization (octahedral shape)
     */
    private void addBondSP3D2() {
        Atom newAtom = new Atom();
        Bond bond = new Bond();
        addAtomInLine(newAtom, bond);
        if (bonds >= 2 && bonds <= 5) {
            Rotate atomRotateZ = new Rotate(90, Atom_Pivot_X, ATOM_PIVOT_Y, ATOM_PIVOT_Z, Rotate.Z_AXIS);
            Rotate atomRotateY = new Rotate(-90 * (bonds - 2), Atom_Pivot_X, ATOM_PIVOT_Y, ATOM_PIVOT_Z, Rotate.Y_AXIS);
            Rotate bondRotateZ = new Rotate(90, BOND_PIVOT_X, BOND_PIVOT_Y, BOND_PIVOT_Z, Rotate.Z_AXIS);
            Rotate bondRotateY = new Rotate(-90 * (bonds - 2), BOND_PIVOT_X, BOND_PIVOT_Y, BOND_PIVOT_Z, Rotate.Y_AXIS);
            newAtom.getTransforms().addAll(atomRotateY, atomRotateZ);
            bond.getTransforms().addAll(bondRotateY, bondRotateZ);
        } else if (bonds == 6 && atom.isInitial()) {
            Rotate atomRotateZ = new Rotate(180, Atom_Pivot_X, ATOM_PIVOT_Y, ATOM_PIVOT_Z, Rotate.Z_AXIS);
            Rotate bondRotateZ = new Rotate(180, BOND_PIVOT_X, BOND_PIVOT_Y, BOND_PIVOT_Z, Rotate.Z_AXIS);
            newAtom.getTransforms().add(atomRotateZ);
            bond.getTransforms().add(bondRotateZ);
        }
    }

    /**
     * Aligns the newAtom and the new bond so that they are above the BondController's
     * atom along its y-axis
     */
    private void addAtomInLine(Atom newAtom, Bond bond) {
        newAtom.getTransforms().addAll(atom.getTransforms());
        bond.getTransforms().addAll(atom.getTransforms());
        bond.getTransforms().add(new Translate(0, -(Bond.BOND_LENGTH + BOND_OFFSET), 0));
        newAtom.getTransforms().add(new Translate(0, -(Bond.BOND_LENGTH + ATOM_OFFSET), 0));
        Group group = new Group();
        group.getChildren().add(bond);
        group.getChildren().add(newAtom);
        bonds++;
        MarsBonds.addToScene(group);
    }

}
