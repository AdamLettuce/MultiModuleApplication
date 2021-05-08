package pl.adamlettuce.authentication.model.domain;


public class Password extends ValueObject<String> {

    public Password() {
    }

    public Password(String value) {
        super(value);
    }
}
