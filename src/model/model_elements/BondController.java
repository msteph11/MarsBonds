package model.model_elements;

import javafx.scene.Group;
import model.Atom;
import model.Bond;
import observers.ApplicationObserver;
import ui.MarsBonds;

/**
 * This controls an Atom's bonds by allowing bonds to be added to it when the B key is pressed
 */
public class BondController implements ApplicationObserver {

    private Atom atom;
    private Hybridization hybridization;

    /**
     * Contstructor
     * Makes a new BondController for given atom with the atom's hybridization (by default sp3)
     * @param atom The atom whose bonds this BondController controls
     */
    public BondController(Atom atom) {
        hybridization = Hybridization.SP3;
        this.atom = atom;
    }


    /**
     * If the B key was pressed, the atom is selected, and its hybridization is sp3,
     * gives the atom another bond
     * @param event signifies which key was pressed (B or C)
     */
    @Override
    public void update(String event) {
        if (atom.isSelected() && event.equals(MarsBonds.B)) {
            switch (hybridization) {
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
    }

    /**
     * Adds a bond to an atom with sp3 hybridization
     */
    private void addBondSP3() {
        Atom newAtom = new Atom();
        Bond bond = new Bond(atom, newAtom);
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
        MarsBonds.addToScene(group);
    }
}
