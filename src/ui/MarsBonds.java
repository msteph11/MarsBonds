package ui;

import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Atom;
import observers.SubjectApplication;

/**
 * Represents the window in which the molecule drawing editor appears.
 * Run the main method to start the program!
 * @author Maria Stephenson
 */
public final class MarsBonds extends SubjectApplication {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private static final Color SCENE_COLOR = Color.LIGHTGREY;
    private static final int CAM_SPEED = 10;
    public static final String C = "C key";
    public static final String B = "B key";
    public static final String DESELECTED = "deselected";

    private static Stage primaryStage;
    private static SubScene scene3D;
    private static Scene scene;
    private static RotateGroup root;
    private static PerspectiveCamera camera;
    private static Text hybridText;
    private static Button hybridButton;

    public static void main( String[] args ) {
        launch(args);
    }

    public void start(Stage primaryStage){
        setSubScene();
        setScene();
        setCamera();
        setPrimaryStage(primaryStage);
    }

    /**
     * Adds given group to the scene3D's root
     * @param group Group to be added to the scene
     */
    public static void addToScene(Group group){
        root.getChildren().add(group);
    }

    /**
     * Creates sub-scene with molecule in center of screen
     * This sub-scene is where all 3D elements will be placed
     */
    private static void setSubScene() {
        Atom atom = new Atom(true);
        root = new RotateGroup();
        root.getChildren().add(atom);
        scene3D = new SubScene(root, WIDTH*3/4, HEIGHT, true, SceneAntialiasing.BALANCED);
        scene3D.setFill(SCENE_COLOR);
    }

    /**
     * Creates scene that has a sub-scene with 3D elements on the left, and
     * a small 2D panel on the right
     */
    private static void setScene() {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(scene3D);
        VBox panel =  new VBox();
        panel.setPrefSize(WIDTH/4, HEIGHT);
        panel.getStyleClass().add("panel");
        borderPane.setRight(panel);
        hybridText = new Text();
        hybridText.getStyleClass().add("text");
        hybridText.setText("Hybridization: ");
        hybridButton = new Button("Change Hybridization");
        panel.getChildren().addAll(hybridText, hybridButton);
        scene = new Scene(borderPane, WIDTH, HEIGHT, true);
        scene.getStylesheets().add("ui/styling/sidePanel.css");
    }
    /**
     * Initializes camera and adds to scene
     */
    private static void setCamera() {
        camera = new PerspectiveCamera();
        camera.setTranslateX(-WIDTH * 2/5);
        camera.setTranslateY(-HEIGHT/2);
        scene3D.setCamera(camera);
    }

    /**
     * Sets up the primary stage by setting its scene, title, adding key
     * and mouse control
     * @param stage the primary stage
     */
    private static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
        addKeyEventHandler();
        addMouseEventHandler();
        addScrollEventHandler();
        primaryStage.setTitle("Mar's Bonds");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Adds KeyEvent handler to primary stage
     * The KeyEvent handler uses input from the WASD keys to rotate
     * the molecule
     * If C is pressed and an atom is selected, its color changes
     * If B is pressed and an atom is selected, that atom gets a new bond
     * (depending on whether it already has the max number of bonds it can make)
     * Arrow keys move camera along x and y axis
     */
    private static void addKeyEventHandler() {
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            switch(e.getCode()) {
                case W:
                    root.rotateInX(-1);
                    break;
                case S:
                    root.rotateInX(1);
                    break;
                case A:
                    root.rotateInY(-1);
                    break;
                case D:
                    root.rotateInY(1);
                    break;
                case C:
                    notifyObservers(C);
                    break;
                case B:
                    notifyObservers(B);
                    break;
                case UP:
                    camera.setTranslateY(camera.getTranslateY() - CAM_SPEED);
                    break;
                case DOWN:
                    camera.setTranslateY(camera.getTranslateY() + CAM_SPEED);
                    break;
                case LEFT:
                    camera.setTranslateX(camera.getTranslateX() - CAM_SPEED);
                    break;
                case RIGHT:
                    camera.setTranslateX(camera.getTranslateX() + CAM_SPEED);
                    break;
            }
        });
    }

    /**
     * Adds MouseEvent handler to primary stage
     * If an atom is clicked on it is selected and the side panel is
     * updated with its information. When the side panel/empty space around the
     * molecule is clicked all atoms are deselected. Only one atom can be selected
     * at a time.
     */
    private static void addMouseEventHandler() {
        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Object result = e.getPickResult().getIntersectedNode();
            notifyObservers(DESELECTED);
            clearAllText();
            if (result instanceof Atom) {
                Atom atomResult = (Atom) result;
                atomResult.setSelected(true);
                updateTextWithAtomInfo(atomResult);
            }
        });
    }

    /**
     * Adds ScrollEvent handler to primary stage
     * User can scroll to zoom in/out
     */
    private static void addScrollEventHandler() {
        primaryStage.addEventHandler(ScrollEvent.SCROLL, e -> {
            camera.setTranslateZ(camera.getTranslateZ() + e.getDeltaY());
        });
    }

    /**
     * Erases any atom information from side panel
     */
    private static void clearAllText() {
        hybridText.setText("Hybridization: ");
    }

    /**
     * Adds information about selected atom to side panel
     * @param atom the atom in the scene that is currently selected
     */
    private static void updateTextWithAtomInfo(Atom atom) {
        hybridText.setText(hybridText.getText() + atom.getHybridization());
    }
}


