package hr.calyx.vjestina2014.domain;

import java.util.Date;

public class Game {
    private Long id;
    private Map map;
    private Date date;
    private Player winner;

    public Game(Map map, Date date) {
        this.map = map;
        this.date = date;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMatchDate(Date matchDate) {
        this.date = matchDate;
    }

    public Date getMatchDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
