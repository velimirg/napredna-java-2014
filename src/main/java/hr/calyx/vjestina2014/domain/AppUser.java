package hr.calyx.vjestina2014.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tomislav on 12/8/2014.
 */
@Entity
public class AppUser {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false, unique = true)
    private String username;

    private String firstName;

    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "app_user_app_role", joinColumns = {@JoinColumn(name = "app_user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "app_role_id", referencedColumnName = "id")})
    private Set<AppRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }
}
