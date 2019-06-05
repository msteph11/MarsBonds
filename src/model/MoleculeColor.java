package model;

import javafx.scene.paint.Color;

/**
 * The colors a molecule can have
 * @author Maria Stephenson
 */
public enum MoleculeColor {

    RED(1,0,0),
    GREEN(0,1,0),
    BLUE(0,0,1),
    TEAL(0,1,1),
    YELLOW(1,1,0),
    HOT_PINK(1,0,.78),
    PURPLE(0.4,0,0.55);

    private Color color;

    /**
     * Constructor
     * Creates a color based on the given rgb values
     */
    MoleculeColor(double r, double g, double b) {
        color = new Color(r, g, b, 1).brighter();
    }

    /**
     * Getter
     * @return the JavaFX Paint Color associated with the constant
     */
    public Color getColor(){
        return color;
    }

    /**
     * @return The next color a molecule of this MoleculeColor will have if
     * the C key is pressed and its selected
     */
    public MoleculeColor getNextColor() {
        switch(this) {
            case RED:
                return GREEN;
            case GREEN:
                return BLUE;
            case BLUE:
                return TEAL;
            case TEAL:
                return YELLOW;
            case YELLOW:
                return HOT_PINK;
            case HOT_PINK:
                return PURPLE;
            case PURPLE:
                return RED;
            default:
                return RED;
        }
    }
}

