package hr.calyx.vjestina2014.domain;

import javax.persistence.*;

@Entity
public class Player {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Race race;

    public Player() {

    }

    public Player(Long id, String name, String nickname, Race r) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.race = r;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Player)) return false;
        if(obj == this) return true;

        Player player = (Player)obj;
        return player.getId().equals(this.getId());
    }
}
