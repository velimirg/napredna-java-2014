package hr.calyx.vjestina2014.domain;

public enum Map {
    OVERGROWTH ("Overgrowth LE"),
    CATALLENA ("Catallena LE");

    private final String name;

    Map(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}
