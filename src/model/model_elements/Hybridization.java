package model.model_elements;

/**
 * Represents the hybridizations a molecule could have
 * @author Maria Stephenson
 */
public enum Hybridization {
    SP3D2("sp³d²"),
    SP3D("sp³d"),
    SP3("sp³"),
    SP2("sp²"),
    SP("sp");

    private String name;

    Hybridization(String name) {
       this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
