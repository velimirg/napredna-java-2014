package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.AppUser;

/**
 * Created by Tomislav on 12/8/2014.
 */
public interface AppUserService {

    public AppUser getByUsername(String username);

    public AppUser create(String username, String firstName, String lastName);
}
