package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.AppRole;
import hr.calyx.vjestina2014.domain.AppUser;
import hr.calyx.vjestina2014.repositories.AppRoleRepository;
import hr.calyx.vjestina2014.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tomislav on 12/8/2014.
 */
@Transactional
@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Override
    public AppUser getByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppUser create(String username, String firstName, String lastName) {
        AppUser user = new AppUser();

        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        Set<AppRole> roles = new HashSet<AppRole>();
        roles.add(appRoleRepository.findByName("USER"));
        user.setRoles(roles);
        appUserRepository.save(user);

        return user;
    }

    @PostConstruct
    private void init() {
        AppRole appRole = new AppRole();
        appRole.setName("USER");
        appRole.setDescription("Korisnik");
        appRoleRepository.save(appRole);

        AppRole admin = new AppRole();
        appRole.setName("ADMIN");
        appRole.setDescription("Administrator");
        admin.setUsers(new HashSet<AppUser>());
        appRoleRepository.save(admin);

        AppUser user = new AppUser();

        user.setUsername("tomislav.barisic@calyx.hr");
        user.setFirstName("Tomislav");
        user.setLastName("Barisic");
        Set<AppRole> roles = new HashSet<AppRole>();
        roles.add(admin);
        admin.getUsers().add(user);
        user.setRoles(roles);
        appUserRepository.save(user);


    }
}
