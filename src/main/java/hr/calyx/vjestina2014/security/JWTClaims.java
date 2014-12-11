package hr.calyx.vjestina2014.security;

/**
 * Created by Tomislav on 12/8/2014.
 */
public class JWTClaims {

    private String email;

    private long exp;

    public JWTClaims() {

    }

    public JWTClaims(String email, long exp) {
        this.email = email;
        this.exp = exp;
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