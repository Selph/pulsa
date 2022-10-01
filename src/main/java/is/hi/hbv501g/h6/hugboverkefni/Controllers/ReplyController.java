package is.hi.hbv501g.h6.hugboverkefni.Controllers;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Content;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Post;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Reply;
import is.hi.hbv501g.h6.hugboverkefni.Services.PostService;
import is.hi.hbv501g.h6.hugboverkefni.Services.ReplyService;
import is.hi.hbv501g.h6.hugboverkefni.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class ReplyController {

    private PostService postService;
    private UserService userService;
    private ReplyService replyService;

    @Autowired
    public ReplyController(PostService postService, UserService userService, ReplyService replyService){
        this.postService = postService;
        this.userService = userService;
        this.replyService = replyService;
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.POST)
    public String replyPost(@PathVariable("id") long id, Reply reply, Content content, BindingResult result, Model model) {
        Optional<Post> post = postService.findPostById(id);
        if(!post.isPresent()) return "postNotFound";
        reply.setContent(content);
        replyService.addNewReply(reply);
        post.get().addReply(reply);
        postService.addNewPost(post.get());
        return "redirect:/post/" + post.get().getPostId();
    }

    @RequestMapping(value = "/post/{postId}/{id}", method = RequestMethod.POST)
    public String nestedReply(@PathVariable("id") long id, @PathVariable("postId") long postId, Reply reply, Content content, BindingResult result, Model model) {
        Optional<Reply> prevReply = replyService.findReplyById(id);

        if(!prevReply.isPresent()) return "postNotFound";

        reply.setContent(content);
        replyService.addNewReply(reply);

        prevReply.get().addReply(reply);
        replyService.addNewReply(prevReply.get());

        return "redirect:/post/" + postId;
    }
}
