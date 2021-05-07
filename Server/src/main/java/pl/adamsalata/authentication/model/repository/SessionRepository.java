package pl.adamsalata.authentication.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adamsalata.authentication.model.entity.Session;
import pl.adamsalata.authentication.model.entity.User;

import java.util.Collection;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
    Session findByToken(String token);
    Collection<Session> findByUsername(String username);
}
