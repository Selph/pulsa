package is.hi.hbv501g.h6.hugboverkefni.Controllers;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.User;

import is.hi.hbv501g.h6.hugboverkefni.Services.Implementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserServiceImplementation userService;

    @Autowired
    public UserController(UserServiceImplementation userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET(User user){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(@Valid User user, BindingResult result){
        userService.addNewUser(user, result);

        if (result.hasErrors()){
            return "register";
        }

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

}



