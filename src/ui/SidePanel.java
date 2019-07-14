package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Atom;
import model.model_elements.Hybridization;
import observers.ApplicationObserver;

public final class SidePanel extends VBox implements ApplicationObserver{

    private static final String HYBRID_DEFAULT_TEXT = "Hybridization: ";
    private static final String HYBRID_BUTTON_DEFAULT_TEXT = "Change Hybridization";
    private static final String ERROR_DEFAULT_TEXT = "";
    private static final ObservableList<String> HYBRIDIZATIONS = FXCollections.observableArrayList(
                                                                    Hybridization.SP.toString(),
                                                                    Hybridization.SP2.toString(),
                                                                    Hybridization.SP3.toString(),
                                                                    Hybridization.SP3D.toString(),
                                                                    Hybridization.SP3D2.toString()
                                                                 );
    // Side panel elements
    private static Text hybridText;
    private static Button hybridButton;
    private static Text errorText;
    private static ComboBox<String> hybridChooser;

    /**
     * Constructor
     * Sets up side panel that is 1/4 of the screen, and has text to print
     * atoms' hybridizations, a button to change hybridizations, and a drop menu
     * that has a list of hybridizations one can pick from
     */
    public SidePanel() {
        setPrefSize(MarsBonds.WIDTH/4, MarsBonds.HEIGHT);
        getStylesheets().add("ui/styling/sidePanel.css");
        getStyleClass().add("panel");
        hybridText = new Text(HYBRID_DEFAULT_TEXT);
        hybridText.getStyleClass().add("text");
        errorText = new Text(ERROR_DEFAULT_TEXT);
        errorText.getStyleClass().addAll("text", "errorText");
        hybridChooser = new ComboBox(HYBRIDIZATIONS);
        hybridChooser.setDisable(true);
        hybridChooser.getStyleClass().add("drop-menu");
        letDropMenuChooseHybridizations();
        hybridButton = new Button(HYBRID_BUTTON_DEFAULT_TEXT);
        addEnablingAndDisablingOfDropMenu();
        getChildren().addAll(hybridText, hybridButton, errorText, hybridChooser);
    }


    /**
     * Erases any atom information from side panel, disables drop menu if user clicks
     * on empty space or different atom
     */
    public static void clearAllText() {
        hybridText.setText(HYBRID_DEFAULT_TEXT);
        errorText.setText(ERROR_DEFAULT_TEXT);
        if (MarsBonds.getCurrSelectedAtom() == null) {
            hybridChooser.setDisable(true);
            hybridChooser.setValue("");
        }
    }

    /**
     * Adds information about currently selected atom to side panel
     */
    public static void updateTextWithAtomInfo() {
        hybridText.setText(HYBRID_DEFAULT_TEXT + MarsBonds.getCurrSelectedAtom().getHybridization());
    }

    /**
     * If the user clicks on the screen (not including side panel buttons)
     * and atom info is displayed on the side panel,
     * it will be cleared, and the hybridization drop menu disabled.
     *
     * If the B key is pressed and the selected atom has >= 1 bonds, the drop
     * menu will be disabled.
     */
    @Override
    public void update(String event) {
        if (event.equals(MarsBonds.DESELECTED)) {
            clearAllText();
        } else if (event.equals(MarsBonds.B) &&
                 MarsBonds.getCurrSelectedAtom() != null &&
                 MarsBonds.getCurrSelectedAtom().getBondController().getNumberOfBonds() >= 1) {
            hybridChooser.setDisable(true);
            hybridChooser.setValue("");
        }

    }

    /**
     * Allows the drop menu to set the selected atom's hybridization.
     */
    private void letDropMenuChooseHybridizations() {
        hybridChooser.setOnAction(e -> {
            String value = hybridChooser.getValue();
            Atom currSelectedAtom = MarsBonds.getCurrSelectedAtom();
            if(currSelectedAtom != null && value != "") {
                if (value.equals(Hybridization.SP.toString()))
                    currSelectedAtom.setHybridization(Hybridization.SP);
                else if (value.equals(Hybridization.SP2.toString()))
                    currSelectedAtom.setHybridization(Hybridization.SP2);
                else if (value.equals(Hybridization.SP3.toString()))
                    currSelectedAtom.setHybridization(Hybridization.SP3);
                else if (value.equals(Hybridization.SP3D.toString()))
                    currSelectedAtom.setHybridization(Hybridization.SP3D);
                else
                    currSelectedAtom.setHybridization(Hybridization.SP3D2);
            }
        });
    }

    /**
     * Allows the "Choose Hybridization" button to enable the drop menu if it is clicked,
     * an atom is selected, and the selected atom has < 2 bonds.
     * Error messages will be printed otherwise.
     */
    private void addEnablingAndDisablingOfDropMenu() {
        hybridButton.setOnAction(e -> {
            Atom currSelectedAtom = MarsBonds.getCurrSelectedAtom();
            if (currSelectedAtom == null) {
                errorText.setText("Please select an atom first.");
            } else if (currSelectedAtom.getBondController().getNumberOfBonds() >= 2) {
                errorText.setText("This atom already has 2 or more \nbonds, therefore you cannot change \nits hybridization.");
            } else {
                hybridChooser.setDisable(false);
            }
        });
    }
}
