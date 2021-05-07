package pl.adamsalata.authentication.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adamsalata.authentication.model.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
}
