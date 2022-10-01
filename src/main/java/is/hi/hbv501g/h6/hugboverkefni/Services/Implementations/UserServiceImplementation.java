package is.hi.hbv501g.h6.hugboverkefni.Services.Implementations;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.User;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.UserRepository;
import is.hi.hbv501g.h6.hugboverkefni.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void addNewUser(User user) {
        Optional<User> userByEmail = this.getUserByUserName(user.getUserName());
        Optional<User> userByUsername = this.getUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        if (userByUsername.isPresent()) {
            throw new IllegalStateException("username taken");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("user with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User editUserName(User user) {
        return null;
    }

    @Override
    public User editRealName(User user) {
        return null;
    }

    @Override
    public User editPassword(User user) {
        return null;
    }

    @Override
    public User editEmail(User user) {
        return null;
    }

    @Override
    public User editAvatar(User user) {
        return null;
    }

    @Override
    public User loginUser(User user) {
        return null;
    }
}
