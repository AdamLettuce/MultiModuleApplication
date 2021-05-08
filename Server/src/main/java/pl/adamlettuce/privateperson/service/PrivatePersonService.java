package pl.adamlettuce.privateperson.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.adamlettuce.privateperson.model.entity.PrivatePerson;
import pl.adamlettuce.privateperson.model.repository.PrivatePersonRepository;

@RestController
@RequestMapping("/private_person")
public class PrivatePersonService {
    @Autowired
    private PrivatePersonRepository privatePersonRepository;

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public boolean add(@RequestBody PrivatePerson privatePerson){
        privatePersonRepository.save(privatePerson);
        return true;
    }
}