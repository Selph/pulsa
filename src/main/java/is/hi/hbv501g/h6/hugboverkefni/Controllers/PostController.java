package is.hi.hbv501g.h6.hugboverkefni.Controllers;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.*;
import is.hi.hbv501g.h6.hugboverkefni.Services.PostService;
import is.hi.hbv501g.h6.hugboverkefni.Services.ReplyService;
import is.hi.hbv501g.h6.hugboverkefni.Services.SubService;
import is.hi.hbv501g.h6.hugboverkefni.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    private PostService postService;
    private UserService userService;
    private ReplyService replyService;
    private SubService subService;

    @Autowired
    public PostController(PostService postService, UserService userService, ReplyService replyService, SubService subService){
        this.postService = postService;
        this.userService = userService;
        this.replyService = replyService;
        this.subService = subService;
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.GET)
    public String newPostGET(Post post){

        return "newPost";
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public String newPostPOST(Content c, Post p, BindingResult result, Model model){
        User user = userService.getUsers().get(0);
        Sub sub = subService.getSubs().get(0);
        Post newPost = new Post(p.getTitle(),
                                sub,
                                c,
                                user,
                                new ArrayList<Voter>(),
                                new ArrayList<Reply>());
        postService.addNewPost(newPost);
        return "redirect:/post/" + newPost.getPostId();
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String postPage(@PathVariable("id") long id, Model model) {
        Optional<Post> post = postService.findPostById(id);
        if(!post.isPresent()) return "postNotFound";

        List<Reply> postReplies = post.get().getReplies();

        model.addAttribute("post", post.get());
        model.addAttribute("postReplies", postReplies);
        model.addAttribute("reply", new Reply());

        return "postPage";
    }
}
