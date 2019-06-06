package model;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import model.model_elements.BondController;
import model.model_elements.AtomColor;
import observers.ApplicationObserver;
import ui.MarsBonds;

/**
 * Represents a molecule
 * @author Maria Stephenson
 */
public class Atom extends Sphere implements ApplicationObserver {
    public final static int RADIUS = 20;

    private BondController bondController;
    private PhongMaterial material;
    private boolean selected;
    private AtomColor color;

    /**
     * Constructor: gives molecule default hybridization (sp3), a default color (teal),
     * and a BondController object
     * When mouse hovers over molecule it is selected, otherwise it isn't
     */
    public Atom() {
        super(RADIUS);
        bondController = new BondController(this);
        MarsBonds.addObserver(bondController);
        MarsBonds.addObserver(this);
        selected = false;
        color = AtomColor.TEAL;
        material = new PhongMaterial(color.getColor().darker());
        setMaterial(material);
        addEventHandlers();
    }

    /**
     * If the molecule is selected and C key was pressed its color changes,
     * otherwise molecule doesn't change
     */
    @Override
    public void update(String event) {
        if(selected && event.equals(MarsBonds.C)) {
            color = color.getNextColor();
            material.setDiffuseColor(color.getColor().brighter());
            setMaterial(material);
        }
    }

    /**
     * Getter
     * @return true if molecule is selected, false otherwise
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Adds two mouse event handlers to detect when mouse enters molecule,
     * and when mouse exits. Atom is selected when mouse enters, and is not
     * selected after mouse leaves.
     *
     * When a molecule is selected, its color is brighter.
     */
    private void addEventHandlers() {
        addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            selected = true;
            material.setDiffuseColor(material.getDiffuseColor().brighter());
            setMaterial(material);
        });
        addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            selected = false;
            material.setDiffuseColor(material.getDiffuseColor().darker());
            setMaterial(material);
        });
    }
}
