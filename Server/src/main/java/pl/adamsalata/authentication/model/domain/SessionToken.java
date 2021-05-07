package pl.adamsalata.authentication.model.domain;


public class SessionToken extends ValueObject<String> {
    public SessionToken(String value) {
        super(value);
    }
}
