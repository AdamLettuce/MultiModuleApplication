package pl.adamlettuce.authentication.model.domain;


public class LoginCredentials {
    private Username username;
    private Password password;

    public LoginCredentials() {
    }

    public LoginCredentials(Username username, Password password) {
        this.username = username;
        this.password = password;
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }
}
