package model.model_elements;

/**
 * Represents the hybridizations a molecule could have
 * @author Maria Stephenson
 */
public enum Hybridization {
    SP3D2("sp³d²", 6),
    SP3D("sp³d", 5),
    SP3("sp³", 4),
    SP2("sp²", 3),
    SP("sp", 2);

    private String name;
    private int maxBonds;

    Hybridization(String name, int maxBonds) {
       this.name = name;
       this.maxBonds = maxBonds;
    }

    public int getMaxBonds() {
        return maxBonds;
    }

    @Override
    public String toString() {
        return name;
    }
}
