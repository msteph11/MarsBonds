package model;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
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
     * When mouse hovers over atom it is selected, otherwise it isn't
     */
    public Atom() {
        super(RADIUS);
        hybridization = Hybridization.SP3;
        bondController = new BondController(this);
        MarsBonds.addObserver(this);
        selected = false;
        color = AtomColor.COLORS[0];
        material = new PhongMaterial(color.darker());
        setMaterial(material);
        addEventHandlers();
    }

    /**
     * If the atom is selected and C key was pressed its color changes
     * If the atom is selected and B key was pressed it gets a new bond
     */
    @Override
    public void update(String event) {
        if(selected)
            switch(event) {
                case MarsBonds.C:
                    color = AtomColor.getNextColor(color);
                    material.setDiffuseColor(color.brighter());
                    setMaterial(material);
                    break;
                case MarsBonds.B:
                   bondController.addBond();
            }
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
     * Adds two mouse event handlers to detect when mouse enters atom,
     * and when mouse exits. Atom is selected when mouse enters, and is not
     * selected after mouse leaves.
     *
     * When an atom is selected, its color is brighter, side panel is
     * updated with the atom's information
     */
    private void addEventHandlers() {
        addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            selected = true;
            material.setDiffuseColor(material.getDiffuseColor().brighter());
            setMaterial(material);
            MarsBonds.updateTextWithAtomInfo(this);
        });
        addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            selected = false;
            material.setDiffuseColor(material.getDiffuseColor().darker());
            setMaterial(material);
            MarsBonds.clearAllText();
        });
    }
}
