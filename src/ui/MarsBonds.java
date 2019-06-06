package ui;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
import model.Atom;
import observers.SubjectApplication;

/**
 * Represents the window in which the molecule drawing editor appears.
 * Run the main method to start the program!
 * @author Maria Stephenson
 */
public class MarsBonds extends SubjectApplication {

    private static final int CAM_NEAR_CLIP = 0;
    private static final int CAM_FAR_CLIP = 1000;
    private static final int CAM_ORG_DISTANCE = -10;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private static final Color SCENE_COLOR = Color.BLACK;
    private static final int CAM_SPEED = 30;
    public static final String C = "C key";
    public static final String B = "B key";

    private static Stage primaryStage;
    private static Scene scene;
    private static Group root;
    private static RotateCamera camera;

    public static void main( String[] args ) {
        launch(args);
    }

    public void start(Stage primaryStage){
        setScene();
        setCamera();
        setPrimaryState(primaryStage);
    }

    /**
     * Adds given group to the scene's root
     * @param group Group to be added to the scene
     */
    public static void addToScene(Group group){
        root.getChildren().add(group);
    }

    /**
     * Creates scene with molecule in center of screen and black background
     */
    private static void setScene() {
        Atom atom = new Atom();
        atom.translateXProperty().set(WIDTH/2);
        atom.translateYProperty().set(HEIGHT/2);
        root = new Group();
        root.getChildren().add(atom);
        scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(SCENE_COLOR);
    }

    /**
     * Initializes camera and adds to scene
     */
    private static void setCamera() {
        camera = new RotateCamera();
        camera.setNearClip(CAM_NEAR_CLIP);
        camera.setFarClip(CAM_FAR_CLIP);
        camera.translateZProperty().set(CAM_ORG_DISTANCE);
        scene.setCamera(camera);
    }

    /**
     * Sets up the primary stage by setting its scene, title, and adding key control
     * @param stage the primary stage
     */
    private static void setPrimaryState(Stage stage) {
        primaryStage = stage;
        addEventHandlers();
        primaryStage.setTitle("Mar's Bonds");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Adds KeyEvent handler to primary stage
     * The KeyEvent handler uses input from the WASD keys to rotate
     * the camera and the arrow keys to move the camera
     * If C is pressed and a molecule is selected, its color changes
     * If B is pressed and a molecule is selected, that molecule gets a new bond
     */
    private static void addEventHandlers() {
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            switch(e.getCode()) {
                case W:
                    camera.rotateInX(-1);
                    break;
                case S:
                    camera.rotateInX(1);
                    break;
                case A:
                    camera.rotateInY(1);
                    break;
                case D:
                    camera.rotateInY(-1);
                    break;
                case UP:
                    camera.setTranslateZ(camera.getTranslateZ() + CAM_SPEED);
                    break;
                case DOWN:
                    camera.setTranslateZ(camera.getTranslateZ() - CAM_SPEED);
                    break;
                case LEFT:
                    camera.setTranslateX(camera.getTranslateX() - CAM_SPEED/3);
                    break;
                case RIGHT:
                    camera.setTranslateX(camera.getTranslateX() + CAM_SPEED/3);
                    break;
                case C:
                    notifyObservers(C);
                    break;
                case B:
                    notifyObservers(B);
                    break;
            }
        });
    }
}


