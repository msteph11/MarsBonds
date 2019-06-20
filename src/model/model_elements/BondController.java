package model.model_elements;

import javafx.scene.Group;
import model.Atom;
import model.Bond;
import ui.MarsBonds;

/**
 * This controls an atom's bonds by allowing bonds to be added to it when the B key is pressed
 * @author Maria Stephenson
 */
public class BondController {

    private Atom atom;
    private int bonds;

    /**
     * Contstructor
     * Makes a new BondController for given atom with no bonds to control
     * @param atom The atom whose bonds this BondController controls
     */
    public BondController(Atom atom) {
        bonds = 0;
        this.atom = atom;
    }


    /**
     * Adds another bond to the atom (if hybridization is sp3 - other hybridizations
     * are yet to be implemented)
     */
    public void addBond() {
        switch (atom.getHybridization()) {
            case SP:
                System.out.println("To be implemented");
                break;
            case SP2:
                System.out.println("To be implemented");
                break;
            case SP3:
                addBondSP3();
                break;
            case SP3D:
                System.out.println("To be implemented");
                break;
            case SP3D2:
                System.out.println("To be implemented");
                break;
            }
    }

    /**
     * Adds a bond to an atom with sp3 hybridization
     */
    private void addBondSP3() {
        Atom newAtom = new Atom();
        Bond bond = new Bond();
        Group group = new Group();
        bond.setTranslateY(atom.getTranslateY() - (Bond.BOND_LENGTH + 5));
        bond.setTranslateX(atom.getTranslateX());
        bond.setTranslateZ(atom.getTranslateZ());
        newAtom.setTranslateX(atom.getTranslateX());
        newAtom.setTranslateY(atom.getTranslateY() - 40 - Bond.BOND_LENGTH);
        newAtom.setTranslateZ(atom.getTranslateZ());
        MarsBonds.addObserver(newAtom);
        group.getChildren().add(bond);
        group.getChildren().add(newAtom);
        bonds++;
        MarsBonds.addToScene(group);
    }
}
