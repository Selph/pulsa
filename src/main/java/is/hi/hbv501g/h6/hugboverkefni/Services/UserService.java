package is.hi.hbv501g.h6.hugboverkefni.Services;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.User;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> getUsers();
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByUserName(String userName);
    void addNewUser(User user);
    void deleteUser(Long userId);
    User editUserName(User user);
    User editRealName(User user);
    User editPassword(User user);
    User editEmail(User user);
    User editAvatar(User user);
    User loginUser(User user);
}
