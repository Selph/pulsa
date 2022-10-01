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
public class PostController {
    private PostService postService;
    private UserService userService;
    private ReplyService replyService;

    @Autowired
    public PostController(PostService postService, UserService userService, ReplyService replyService){
        this.postService = postService;
        this.userService = userService;
        this.replyService = replyService;
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.GET)
    public String newPostGET(Post post){

        return "newPost";
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public String newPostPOST(Content c, Post p, BindingResult result, Model model){
        User user = userService.getUsers().get(0);
        c.setCreated(LocalDate.now());
        p.setContent(c);
        postService.addNewPost(p);
        return "redirect:/post/" + p.getPostId();
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String postPage(@PathVariable("id") long id, Model model) {
        Optional<Post> post = postService.findPostById(id);
        if(!post.isPresent()) return "postNotFound";

        List<Reply> postReplies = post.get().getReplies();
        List<Reply> allReplies = new ArrayList<Reply>();
        postReplies.forEach(item -> {
            Optional<Reply> reply = replyService.findReplyById(item);
            if(reply.isPresent()) allReplies.add(reply.get());
        });

        model.addAttribute("post", post.get());
        model.addAttribute("allReplies", allReplies);
        model.addAttribute("reply", new Reply());
        return "postPage";
    }
}
