package model;

/**
 * Represents the hybridizations a molecule could have
 */
public enum Hybridization {
    SP3D2("sp3d2"),
    SP3D("sp3d"),
    SP3("sp3"),
    SP2("sp3"),
    SP("sp");

    private String type;

    /**
     * Constructor
     * @param type type of hybridization a molecule could have
     */
    Hybridization(String type) {
        this.type = type;
    }

    /**
     * Getter
     * @return tyoe of hybridization
     */
    public String getType() {
        return type;
    }
}
