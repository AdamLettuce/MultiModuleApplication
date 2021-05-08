package pl.adamlettuce.authentication.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.adamlettuce.authentication.SessionTokenGenerator;
import pl.adamlettuce.authentication.model.entity.Session;
import pl.adamlettuce.authentication.model.entity.User;
import pl.adamlettuce.authentication.model.repository.SessionRepository;
import pl.adamlettuce.authentication.model.repository.UserRepository;

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationService {

    private static final String LOGIN_FAILED = "login_failed";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    private SessionTokenGenerator sessionTokenGenerator = new SessionTokenGenerator();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody String login(@RequestBody User user) {
        if (userIsValid(user, userRepository.findByUsername(user.getUsername()))) {
            return authenticateUser(user);
        }
        return LOGIN_FAILED;
    }

    private boolean userIsValid(User user, User userToCheck) {
        return BCrypt.checkpw(user.getPassword(), userToCheck.getPassword());
    }

    private String authenticateUser(User user) {
        deleteUserSessions(user);
        return newPersistedSession(Session.newInstance(user, new Date(),
                    sessionTokenGenerator.generate(user.getUsername()))).getToken();
    }

    private void deleteUserSessions(User user) {
        Collection<Session> sessions = sessionRepository.findByUsername(user.getUsername());
        if(!CollectionUtils.isEmpty(sessions)) {
            sessionRepository.delete(sessions);
        }
    }

    private Session newPersistedSession(Session session) {
        sessionRepository.save(session);
        return session;
    }

}