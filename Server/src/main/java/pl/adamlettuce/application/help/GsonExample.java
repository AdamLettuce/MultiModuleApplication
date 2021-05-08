package pl.adamlettuce.application.help;


import com.google.gson.Gson;
import pl.adamlettuce.address.model.entity.Address;
import pl.adamlettuce.authentication.model.domain.LoginCredentials;
import pl.adamlettuce.authentication.model.domain.Password;
import pl.adamlettuce.authentication.model.domain.Username;
import pl.adamlettuce.privateperson.model.entity.PrivatePerson;

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
