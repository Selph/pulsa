package is.hi.hbv501g.h6.hugboverkefni.Controllers;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Post;
import is.hi.hbv501g.h6.hugboverkefni.Services.PostService;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Reply;
import is.hi.hbv501g.h6.hugboverkefni.Services.ReplyService;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.User;
import is.hi.hbv501g.h6.hugboverkefni.Services.UserService;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    private PostService postService;
    private UserService userService;
    private ReplyService replyService;
    @Autowired
    public MainController(PostService postService, UserService userService, ReplyService replyService){
        this.postService = postService;
        this.userService = userService;
        this.replyService = replyService;
    }

    @RequestMapping("/")
    public String frontPage(Model model) {
        List<Post> allPosts = postService.getPosts();

        model.addAttribute("posts", allPosts);

        return "frontPage";
    }
}
