package is.hi.hbv501g.h6.hugboverkefni.Controllers;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.User;

import is.hi.hbv501g.h6.hugboverkefni.Services.Implementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


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
        if (result.hasErrors()){
            return "redirect:/register";
        }

        userService.addNewUser(user);
        return "redirect:/registrationSuccess";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "login";
    }

    @RequestMapping(value = "/registrationSuccess", method = RequestMethod.GET)
    public String registrationSuccessGET(){
        return "registrationSuccess";
    }

}
