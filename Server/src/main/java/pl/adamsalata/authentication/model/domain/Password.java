package pl.adamsalata.authentication.model.domain;


import java.util.Optional;

public class Password extends ValueObject<String> {

    public Password() {
    }

    public Password(String value) {
        super(value);
    }
}
