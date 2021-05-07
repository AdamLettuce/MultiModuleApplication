package pl.adamsalata.authentication;


import pl.adamsalata.authentication.model.domain.SessionToken;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class PasswordEncoder {

    private static final String ALGORITHM_SHA_256 = "SHA-256";
    private static final String ALGORITHM_PBE_WITH_HMAC_SHA1_AND_DE_SEDE = "PBEWithHmacSHA1AndDESede";
    private static final String CHARSET_NAME = "UTF-8";
    private static final int TOKEN_LENGTH = 10;

    public Optional<SessionToken> create(String toTokenize) {
        Optional<SessionToken> result = Optional.empty();
        Optional<MessageDigest> messageDigest = getMessageDigest();
        Optional<byte[]> hash = Optional.empty();
        if (messageDigest.isPresent()) {
            hash = getHash(toTokenize, messageDigest.get());
        }
        if (hash.isPresent()) {
            result = Optional.of(new SessionToken(getHashText(hash).get().substring(0, TOKEN_LENGTH)));
        }
        return result;
    }

    private Optional<MessageDigest> getMessageDigest() {
        Optional<MessageDigest> messageDigest = Optional.empty();
        try {
            messageDigest = Optional.of(MessageDigest.getInstance(ALGORITHM_SHA_256));
        } catch (NoSuchAlgorithmException e) {
            //Optional.of throws NullPointerException
        }
        return messageDigest;
    }

    private Optional<byte[]> getHash(String text, MessageDigest messageDigest) {
        Optional<byte[]> result = Optional.empty();
        try {
            result = Optional.of(messageDigest.digest((text + String.valueOf(System.currentTimeMillis())).getBytes
                    (CHARSET_NAME)));
        } catch (UnsupportedEncodingException e) {
            //Optional.of throws NullPointerException
        }
        return result;
    }

    private Optional<String> getHashText(Optional<byte[]> hash) {
        Optional<String> result = Optional.empty();
        try {
            result = Optional.of(new String(hash.get(), CHARSET_NAME));
        } catch (UnsupportedEncodingException e) {
            //Optional.of throws NullPointerException
        }
        return result;
    }
}
