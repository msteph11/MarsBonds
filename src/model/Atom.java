package model;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import model.model_elements.BondController;
import model.model_elements.AtomColor;
import model.model_elements.Hybridization;
import observers.ApplicationObserver;
import ui.MarsBonds;


/**
 * Represents a atom
 * @author Maria Stephenson
 */
public class Atom extends Sphere implements ApplicationObserver {
    public final static int RADIUS = 20;

    private BondController bondController;
    private PhongMaterial material;
    private boolean selected;
    private Color color;
    private Hybridization hybridization;

    /**
     * Constructor: gives atom default hybridization (sp3), a default color (red),
     * and a BondController object
     * When mouse clicks on atom it is selected, otherwise it isn't
     */
    public Atom() {
        super(RADIUS);
        hybridization = Hybridization.SP3;
        bondController = new BondController(this);
        MarsBonds.addObserver(this);
        selected = false;
        color = AtomColor.COLORS[0];
        material = new PhongMaterial(color.brighter());
        setMaterial(material);
    }

    /**
     * If the atom is selected and C key is pressed its color changes
     * If the atom is selected and B key is pressed it gets a new bond
     * If the user clicks on a different atom/elsewhere in the scene, the
     * atom is no longer selected
     */
    @Override
    public void update(String event) {
        if(selected)
            switch(event) {
                case MarsBonds.C:
                    color = AtomColor.getNextColor(color);
                    changeMaterial();
                    break;
                case MarsBonds.B:
                   bondController.addBond();
                   break;
                case MarsBonds.DESELECTED:
                    setSelected(false);
                    break;
            }
    }

    /**
     * Setter
     * NOTE: when atom is selected its color is darker
     * @param selected if true, atom is selected, else it's not
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        changeMaterial();
    }
    /**
     * Getter
     * @return true if atom is selected, false otherwise
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Getter
     * @return atom's hybridization
     */
    public Hybridization getHybridization() {
        return hybridization;
    }

    /**
     * Changes the atom's material so that it has the given color
     * The color is darker if its selected, brighter if its not
     */
    private void changeMaterial() {
        if(selected)
            material.setDiffuseColor(color.darker());
        else
            material.setDiffuseColor(color.brighter());
        setMaterial(material);
    }
}
