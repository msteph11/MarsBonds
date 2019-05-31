import com.jogamp.opengl.*;

import javax.swing.*;

import ui.MoleculeDrawingCanvas;

/**
 * Represents the main frame in which the molecule drawing editor appears.
 * Run the main method to start the program!
 * @author Maria Stephenson
 */
public class MarsBonds {

    public final static int SCREEN_WIDTH = 2000;
    public final static int SCREEN_HEIGHT = 1500;
    public final static int CANVAS_WIDTH = 1000;
    public final static int CANVAS_HEIGHT = 1500;

    public static void main( String[] args ) {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        final MoleculeDrawingCanvas canvas = new MoleculeDrawingCanvas(capabilities, CANVAS_WIDTH, CANVAS_HEIGHT);

        final JFrame frame = new JFrame("Mar's Bonds");
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}


