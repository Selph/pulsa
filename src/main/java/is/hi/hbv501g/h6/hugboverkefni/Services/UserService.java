package is.hi.hbv501g.h6.hugboverkefni.Services;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> getUsers();

    User getAnon();

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByUserName(String userName);
    User getUserObjectByUserName(String userName);
    void addNewUser(User user, BindingResult result);

    void deleteUser(Long userId);

    void editUserName(User user);

    User editRealName(User user);

    User editPassword(User user);

    void editEmail(User user);

    User editAvatar(User user);

    User loginUser(User user);
}
