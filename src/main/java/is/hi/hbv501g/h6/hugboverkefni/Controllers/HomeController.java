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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public String newPostPOST(Content c, Post p, BindingResult result, Model model){
        System.out.println(c.getImage());
        System.out.println(c.getText());
        System.out.println(c.getAudio());
        User user = userService.getUsers().get(0);
        c.setCreated(LocalDate.now());
        p.setContent(c);
        postService.addNewPost(p);
        System.out.println(p.getPostId());
        System.out.println(p.getSub());
        return "redirect:/post/" + p.getPostId();
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String postPage(@PathVariable("id") long id, Model model) {
        Optional<Post> post = postService.findPostById(id);
        if(!post.isPresent()) return "postNotFound";
        model.addAttribute("post", post.get());
        return "postPage";
    }
}
