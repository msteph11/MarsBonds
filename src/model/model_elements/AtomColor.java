package model.model_elements;

import javafx.scene.paint.Color;

/**
 * The colors a molecule can have
 * @author Maria Stephenson
 */
public enum AtomColor {

    RED(1,0,0),
    ORANGE(1,0.4,0),
    YELLOW(1,1,0),
    GREEN(0,1,0),
    TEAL(0,1,1),
    BLUE(0,0,1),
    PURPLE(0.4,0,0.55),
    HOT_PINK(1,0,.78);

    private Color color;

    /**
     * Constructor
     * Creates a color based on the given rgb values
     */
    AtomColor(double r, double g, double b) {
        color = new Color(r, g, b, 1).brighter();
    }

    /**
     * Getter
     * @return the JavaFX Paint Color associated with the AtomColor
     */
    public Color getColor(){
        return color;
    }

    /**
     * @return The next color a molecule of this AtomColor will have if
     * the C key is pressed and its selected
     */
    public AtomColor getNextColor() {
        switch(this) {
            case RED:
                return ORANGE;
            case ORANGE:
                return YELLOW;
            case YELLOW:
                return GREEN;
            case GREEN:
                return TEAL;
            case TEAL:
                return BLUE;
            case BLUE:
                return PURPLE;
            case PURPLE:
                return HOT_PINK;
            case HOT_PINK:
                return RED;
            default:
                return RED;
        }
    }
}

