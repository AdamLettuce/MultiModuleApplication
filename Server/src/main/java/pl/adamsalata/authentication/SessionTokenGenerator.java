package pl.adamsalata.authentication;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import pl.adamsalata.authentication.model.domain.SessionToken;

public class SessionTokenGenerator {

    public SessionToken generate(String toTokenize) {
        return new SessionToken(Hashing.sha256()
                .hashString(getStringToTokenize(toTokenize, getSalt()), Charsets.UTF_8)
                .toString());
    }

    private String getSalt() {
        return String.valueOf(System.currentTimeMillis());
    }

    private String getStringToTokenize(String toTokenize, String salt) {
        return new StringBuilder()
                .append(toTokenize)
                .append(salt)
                .toString();
    }

}
