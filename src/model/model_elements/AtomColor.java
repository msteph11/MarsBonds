package model.model_elements;

import javafx.scene.paint.Color;

/**
 * The colors an atom can have
 * @author Maria Stephenson
 */
public final class AtomColor {

    public final static Color[] COLORS = {Color.RED, Color.ORANGE, Color.YELLOW, Color.LAWNGREEN, Color.GREEN,
                                          Color.TEAL, Color.SKYBLUE, Color.DEEPSKYBLUE, Color.BLUE, Color.BLUEVIOLET,
                                          Color.MEDIUMPURPLE, Color.LAVENDER, Color.HOTPINK, Color.FUCHSIA};

    /**
     * @return The next color an atom will have if the C key is
     * pressed and its selected
     * @param c the color the atom currently has
     */
    public static Color getNextColor(Color c) {
        for(int index = 0; index < COLORS.length - 1; index++) {
            if (c.equals(COLORS[index])) {
                return COLORS[index + 1];
            }
        }
        return COLORS[0];
    }
}

