package pl.adamlettuce.authentication.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adamlettuce.authentication.model.entity.Session;

import java.util.Collection;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
    Session findByToken(String token);
    Collection<Session> findByUsername(String username);
}
