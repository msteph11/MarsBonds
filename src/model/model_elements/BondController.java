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
        Atom newAtom = new Atom();
        Bond bond = new Bond();
        newAtom.getTransforms().addAll(atom.getTransforms());
        bond.getTransforms().addAll(atom.getTransforms());
        bond.getTransforms().add(new Translate(0, -(Bond.BOND_LENGTH + BOND_OFFSET), 0));
        newAtom.getTransforms().add(new Translate(0, -(Bond.BOND_LENGTH + ATOM_OFFSET), 0));
        Group group = new Group();
        group.getChildren().add(bond);
        group.getChildren().add(newAtom);
        if (bonds > 0) {
            Rotate rotateZ = new Rotate(-109.5, Rotate.Z_AXIS);
            Rotate rotateY = new Rotate(-109.5 * (bonds - 1), Rotate.Y_AXIS);
            group.getTransforms().addAll(rotateY, rotateZ);
        }
        addBondToScene(group);
    }

    private void addBondToScene(Group group) {
        bonds++;
        MarsBonds.addToScene(group);
    }
}
