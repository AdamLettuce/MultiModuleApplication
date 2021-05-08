package pl.adamlettuce.authentication.model;


import java.util.Date;

public class AuthenticationToken {

    private String key;
    private long keyCreationTime;
    private String extendedInformation;

    public static AuthenticationToken getOkInstance(String key) {
        return new AuthenticationToken(key, "ok", getCurrentTime());
    }

    public static AuthenticationToken getFailedInstance() {
        return new AuthenticationToken("failed","failed", getCurrentTime());
    }

    private static long getCurrentTime() {
        return new Date().getTime();
    }

    private AuthenticationToken(String key, String extendedInformation, Long keyCreationTime) {
        this.key = key;
        this.extendedInformation = extendedInformation;
        this.keyCreationTime = keyCreationTime;
    }

    public String getKey() {
        return key;
    }

    public long getKeyCreationTime() {
        return keyCreationTime;
    }

    public String getExtendedInformation() {
        return extendedInformation;
    }
}
