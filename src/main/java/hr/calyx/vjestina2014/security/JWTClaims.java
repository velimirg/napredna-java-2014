package hr.calyx.vjestina2014.security;

import java.util.List;

/**
 * Created by Tomislav on 12/8/2014.
 */
public class JWTClaims {

    private String email;

    private long exp;

    private List<String> roles;

    public JWTClaims() {

    }

    public JWTClaims(String email, long exp, List<String> roles) {
        this.email = email;
        this.exp = exp;
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return email + " " + exp;
    }
}