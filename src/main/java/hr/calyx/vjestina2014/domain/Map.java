package hr.calyx.vjestina2014.domain;

public enum Map {
    OVERGROWTH ("Overgrowth"),
    DEADWING ("Deadwing"),
    FOXTROT ("Foxtrot Labs"),
    KING ("King Sejong Station"),
    MERRY ("Merry Go Round"),
    NIMBUS ("Nimbus"),
    CATALLENA ("Catallena LE");

    private final String name;

    Map(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}
