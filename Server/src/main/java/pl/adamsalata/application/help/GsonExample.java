package pl.adamsalata.application.help;


import com.google.gson.Gson;
import pl.adamsalata.address.model.entity.Address;
import pl.adamsalata.authentication.model.domain.LoginCredentials;
import pl.adamsalata.authentication.model.domain.Password;
import pl.adamsalata.authentication.model.domain.Username;
import pl.adamsalata.privateperson.model.entity.PrivatePerson;

public class GsonExample {
    public static void main(String[] args) {
        privatePersonGson();
    }

    private static void privatePersonGson() {
        PrivatePerson privatePerson = new PrivatePerson();

        privatePerson.setFirstName("Jan");
        privatePerson.setSecondName("Stefan");
        privatePerson.setLastName("Kowalski");

        Address address = new Address();
        address.setCity("Katowice");
        address.setStreet("Kartofla 8/55");
        address.setPostalCode("02-123");
        privatePerson.addAddress(address);

        System.out.println(new Gson().toJson(privatePerson));
    }

    private static void loginCredentialsGson() {
        Username username = new Username("test");
        Password password = new Password("test");
        LoginCredentials loginCredentials = new LoginCredentials(username, password);
        System.out.println(new Gson().toJson(loginCredentials));
    }

}
