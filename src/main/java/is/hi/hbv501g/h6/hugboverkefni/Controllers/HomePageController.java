package is.hi.hbv501g.h6.hugboverkefni.Controllers;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Post;
import is.hi.hbv501g.h6.hugboverkefni.Services.Implementations.PostServiceImplementation;
import is.hi.hbv501g.h6.hugboverkefni.Services.Implementations.ReplyServiceImplementation;
import is.hi.hbv501g.h6.hugboverkefni.Services.Implementations.UserServiceImplementation;
import is.hi.hbv501g.h6.hugboverkefni.Services.PostService;
import is.hi.hbv501g.h6.hugboverkefni.Services.ReplyService;
import is.hi.hbv501g.h6.hugboverkefni.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomePageController {
    private PostServiceImplementation postService;
    private UserServiceImplementation userService;
    private ReplyServiceImplementation replyService;
    @Autowired
    public HomePageController(PostServiceImplementation postService, UserServiceImplementation userService, ReplyServiceImplementation replyService){
        this.postService = postService;
        this.userService = userService;
        this.replyService = replyService;
    }

    @RequestMapping("/")
    public String frontPage(Model model) {
        List<Post> allPosts = postService.getPostsOrderedByCreated();

        model.addAttribute("posts", allPosts);

        return "frontPage";
    }
}
