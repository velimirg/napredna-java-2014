package hr.calyx.vjestina2014.repositories;

import hr.calyx.vjestina2014.domain.AppRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomislav on 12/8/2014.
 */
@Repository
public interface AppRoleRepository extends CrudRepository<AppRole, Long> {

    AppRole findByName(String name);

}
