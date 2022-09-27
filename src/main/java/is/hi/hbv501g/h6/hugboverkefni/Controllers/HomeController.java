package is.hi.hbv501g.h6.hugboverkefni.Controllers;

import is.hi.hbv501g.h6.hugboverkefni.post.Post;
import is.hi.hbv501g.h6.hugboverkefni.post.PostService;
import is.hi.hbv501g.h6.hugboverkefni.user.User;
import is.hi.hbv501g.h6.hugboverkefni.user.UserService;
import is.hi.hbv501g.h6.hugboverkefni.util.Content;
import is.hi.hbv501g.h6.hugboverkefni.util.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private PostService postService;
    private UserService userService;
    @Autowired
    public HomeController(PostService postService, UserService userService){
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String frontPage(Model model) {
        List<Post> allPosts = postService.getPosts();

        model.addAttribute("posts", allPosts);

        return "frontPage";
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.GET)
    public String newPostGET(Post post){

        return "newPost";
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public String newPostPOST(HttpServletRequest req, Model model){

        User user = userService.getUsers().get(0);
        Content content = new Content(req.getParameter("text"),"mynd.jpg", "audio.mp3", LocalDate.now() );
        Post post = new Post(req.getParameter("title"),1,content,user,new ArrayList<Voter>(), new ArrayList<Integer>(), LocalDate.now());
        postService.addNewPost(post);
        return "redirect:/";
    }
}
