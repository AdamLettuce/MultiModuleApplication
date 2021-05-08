package pl.adamlettuce.privateperson.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adamlettuce.privateperson.model.entity.PrivatePerson;

import java.util.Set;

@Repository
public interface PrivatePersonRepository extends CrudRepository<PrivatePerson, Long> {
    Set<PrivatePerson> findByFirstName(String firstName);
    PrivatePerson findOne(Long id);
    Set<PrivatePerson> findAll();
    Set<PrivatePerson> findByLastName(String lastName);
}