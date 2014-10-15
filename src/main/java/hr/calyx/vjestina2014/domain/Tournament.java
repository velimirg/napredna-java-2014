package hr.calyx.vjestina2014.domain;

import java.util.List;

public class Tournament {
    private List<Round> rounds;
    private String name;

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
