package pl.adamsalata.address.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adamsalata.address.model.entity.Address;


import java.util.List;


@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findByPostalCode(String postalCode);
    List<Address> findByStreet(String street);
    List<Address> findByCity(String city);
}