package is.hi.hbv501g.h6.hugboverkefni.Controllers;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.User;

import is.hi.hbv501g.h6.hugboverkefni.Services.CloudinaryService;
import is.hi.hbv501g.h6.hugboverkefni.Services.Implementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserServiceImplementation userService;

    private final CloudinaryService cloudinaryService;

    @Autowired
    public UserController(UserServiceImplementation userService, CloudinaryService cloudinaryService) {
        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET(User user){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(@Valid User user, BindingResult result){
        int exists = userService.userExists(user);

        if(exists == 1) {
            result.rejectValue("userName", "error.duplicate", "Username taken");
        } else if(exists == 2) {
            result.rejectValue("email", "error.duplicate", "Email in use");
        } else if(exists == 3) {
            result.rejectValue("userName", "error.duplicate", "Username taken");
            result.rejectValue("email", "error.duplicate", "Email in use");
        }

        if (result.hasErrors()){
            return "register";
        }

        userService.addNewUser(user);

        return "redirect:/registrationSuccess";
    }

    @RequestMapping(name = "/login", value = "/login", method = RequestMethod.GET)
    public String loginGET(User user){
        return "login";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String loginPOST(User user, HttpSession session) {
        User auth = userService.loginUser(user);
        if(auth == null) return "redirect:login?error";

        // Session time limit er 1800s eða 30m
        // Breyta session time limit -> session.setMaxInactiveInterval(sec);
        session.setAttribute("user", auth);

        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        if(session.getAttribute("user") != null)  {
            session.invalidate();
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/registrationSuccess", method = RequestMethod.GET)
    public String registrationSuccessGET(){
        return "registrationSuccess";
    }

    @RequestMapping(value = "/user/{id}/edit", method = RequestMethod.GET)
    public String editAccountGET(@PathVariable("id") long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user.getUser_id() != id) return "postNotFound";
        return "editAccount";
    }

    @RequestMapping(value = "/user/{id}/edit", method = RequestMethod.POST)
    public String editAccountPOST(String realName, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if(realName.equals(user.getRealName())) return "editAccount";

        user.setRealName(realName);
        userService.addNewUser(user);
        model.addAttribute("updated", true);

        return "editAccount";
    }

    @RequestMapping(value = "/user/{id}/edit/avatar", method = RequestMethod.GET)
    public String changeAvatarGET() {
        return "editAccountAvatar";
    }

    @RequestMapping(value = "/user/{id}/edit/avatar", method = RequestMethod.POST)
    public String changeAvatarPOST(@RequestParam MultipartFile avatar, HttpSession session, Model model) {
        if(avatar.isEmpty()) {
            model.addAttribute("avatar", true);
            return "editAccountAvatar";
        }

        User user = (User) session.getAttribute("user");
        String avatarUrl = cloudinaryService.uploadImage(avatar);
        user.setAvatar(avatarUrl);
        userService.addNewUser(user);

        return "editAccountAvatar";
    }

    @RequestMapping(value = "/user/{id}/edit/username", method = RequestMethod.GET)
    public String changeUsernameGET() {
        return "editAccountUsername";
    }

    @RequestMapping(value = "/user/{id}/edit/username", method = RequestMethod.POST)
    public String changeUsernamePOST(String username, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if(username.equals(user.getUserName())) return "editAccountUsername";

        model.addAttribute("updated", true);
        user.setUserName(username);
        userService.addNewUser(user);

        return "editAccountUsername";
    }

    @RequestMapping(value = "/user/{id}/edit/password", method = RequestMethod.GET)
    public String changePasswordGET() {
        return "editAccountPassword";
    }

    @RequestMapping(value = "/user/{id}/edit/password", method = RequestMethod.POST)
    public String changePasswordPOST(String password, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if(user.getPassword().equals(password) || password.isBlank()) return "editAccountPassword";

        model.addAttribute("updated", true);
        user.setPassword(password);
        userService.addNewUser(user);

        return "editAccountPassword";
    }

    @RequestMapping(value = "/user/{id}/edit/email", method = RequestMethod.GET)
    public String changeEmailGET() {
        return "editAccountEmail";
    }

    @RequestMapping(value = "/user/{id}/edit/email", method = RequestMethod.POST)
    public String changeEmailPOST(String email, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if(email.equals(user.getEmail())) return "editAccountEmail";

        model.addAttribute("updated", true);
        user.setEmail(email);
        userService.addNewUser(user);

        return "editAccountEmail";
    }
}



