package hr.calyx.vjestina2014.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private boolean template;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "tournament")
    private List<Round> rounds;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_template_id")
    @JsonIgnore
    private Tournament parentTemplate;

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

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public boolean isTemplate() {
        return template;
    }

    public void setTemplate(boolean template) {
        this.template = template;
    }

    public Tournament getParentTemplate() {
        return parentTemplate;
    }

    public void setParentTemplate(Tournament parentTemplate) {
        this.parentTemplate = parentTemplate;
    }
}
