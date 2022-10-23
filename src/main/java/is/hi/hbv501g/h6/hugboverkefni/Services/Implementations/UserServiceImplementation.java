package is.hi.hbv501g.h6.hugboverkefni.Services.Implementations;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.User;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.UserRepository;
import is.hi.hbv501g.h6.hugboverkefni.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    @Autowired
    private Validator validator;
    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getAnon() {
        Optional<User> anon = userRepository.findById(3L);
        return anon.get();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void addNewUser(User user, BindingResult result) {
        Optional<User> userName = userRepository.findByUserName(user.getUserName());
        Optional<User> email = userRepository.findByEmail(user.getEmail());

        if(userName.isPresent()) result.rejectValue("userName", "error.duplicate", "Username taken");
        if(email.isPresent()) result.rejectValue("email", "error.duplicate", "Email in use");

        if(!result.hasErrors()) userRepository.save(user);
    }


    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("user with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public void editUserName(User user) {
        Optional<User> usernameExists = userRepository.findByUserName(user.getUserName());
        if(usernameExists.isPresent()) throw new DuplicateKeyException("Username taken");
        userRepository.save(user);
    }

    @Override
    public User editRealName(User user) {
        return userRepository.save(user);
    }

    @Override
    public User editPassword(User user) {
        return userRepository.save(user);
    }

    @Override
    public void editEmail(User user) {
        Optional<User> userEmail = userRepository.findByEmail(user.getEmail());
        if(userEmail.isPresent()) throw new DuplicateKeyException("Email taken");
        userRepository.save(user);
    }

    @Override
    public User editAvatar(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loginUser(User user) {
        Optional<User> exists = getUserByUserName(user.getUserName());

        if(exists.isPresent()) {
            if(exists.get().getPassword().equals(user.getPassword())) return exists.get();
        }
        return null;
    }
}
