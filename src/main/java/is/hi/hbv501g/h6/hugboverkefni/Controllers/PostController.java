package is.hi.hbv501g.h6.hugboverkefni.Controllers;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.*;
import is.hi.hbv501g.h6.hugboverkefni.Services.CloudinaryService;
import is.hi.hbv501g.h6.hugboverkefni.Services.Implementations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    private PostServiceImplementation postService;
    private UserServiceImplementation userService;
    private ReplyServiceImplementation replyService;
    private SubServiceImplementation subService;
    private VoteServiceImplementation voteService;
    private CloudinaryService cloudinaryService;

    @Autowired
    public PostController(PostServiceImplementation postService,
                          UserServiceImplementation userService,
                          ReplyServiceImplementation replyService,
                          SubServiceImplementation subService,
                          VoteServiceImplementation voteService,
                          CloudinaryService cloudinaryService){
        this.postService = postService;
        this.userService = userService;
        this.replyService = replyService;
        this.subService = subService;
        this.voteService = voteService;
        this.cloudinaryService = cloudinaryService;
    }


    @RequestMapping(value = "/p/{slug}/{id}", method = RequestMethod.GET)
    public String postPage(@PathVariable("id") long id, Model model) {
        Optional<Post> post = postService.getPostById(id);
        if(!post.isPresent()) return "postNotFound";

        model.addAttribute("post", post.get());
        model.addAttribute("postReplies", post.get().getReplies());
        model.addAttribute("reply", new Reply());
        model.addAttribute("content", new Content());
        return "postPage";
    }

    @RequestMapping(value = "/p/{slug}/newPost", method = RequestMethod.GET)
    public String newPostGET(@PathVariable String slug, Post post, Model model){
        model.addAttribute("slug", slug);
        return "newPost";
    }

    @RequestMapping(value = "/p/{slug}/newPost", method = RequestMethod.POST)
    public String newPostPOST(@PathVariable String slug, String title, String text, @RequestParam("image") MultipartFile image, @RequestParam("audio") MultipartFile audio, @RequestParam("recording") String recording, Model model, HttpSession session){
        Sub sub = subService.getSubBySlug(slug);
        Post newPost = createPost(title, sub, text, image, audio, recording, session);
        postService.addNewPost(newPost);
        return "redirect:/p/" + slug + '/' + newPost.getPostId();
    }

    @RequestMapping(value = "/p/{slug}/{id}", method = RequestMethod.POST)
    public String replyPost(@PathVariable String slug, @PathVariable("id") long id, String text, @RequestParam("image") MultipartFile image, @RequestParam("audio") MultipartFile audio, @RequestParam("recording") String recording, Model model, HttpSession session) {
        Optional<Post> post = postService.getPostById(id);
        if(!post.isPresent()) return "postNotFound";

        Reply reply = createReply(text, image, audio, recording, session);
        replyService.addNewReply(reply);
        post.get().addReply(reply);
        postService.addNewPost(post.get());

        return "redirect:/p/" + slug + '/' + post.get().getPostId();
    }


    @RequestMapping(value = "/p/{slug}/{postId}/{id}", method = RequestMethod.POST)
    public String replyReply(@PathVariable String slug, @PathVariable("postId") long postId, @PathVariable("id") long id, String text, @RequestParam("image") MultipartFile image, @RequestParam("audio") MultipartFile audio,@RequestParam("recording") String recording, Model model, HttpSession session) {
        Optional<Reply> prevReply = replyService.getReplyById(id);
        if(!prevReply.isPresent()) return "postNotFound";

        Reply reply = createReply(text, image, audio, recording, session);
        replyService.addNewReply(reply);
        prevReply.get().addReply(reply);
        replyService.addNewReply(prevReply.get());

        return "redirect:/p/" + slug + '/' + postId;
    }

    @RequestMapping(value = "/p/{slug}/{postId}/{id}/vote", method = RequestMethod.GET)
    @ResponseBody
    public String getReplyVote(@PathVariable String slug, @PathVariable("postId") long postId, @PathVariable("id") long id, Model model) {
        Reply reply = replyService.getReplyById(id).get();

        return reply.getVote().toString();
    }

    @RequestMapping(value = "/p/{slug}/{postId}/{id}/upvote", method = RequestMethod.POST)
    public String upvoteReply(@PathVariable String slug, @PathVariable("postId") long postId, @PathVariable("id") long id, HttpSession session) {

        return changeReplyVote(slug, postId, id, true, session);
    }

    @RequestMapping(value = "/p/{slug}/{postId}/{id}/downvote", method = RequestMethod.POST)
    public String downvoteReply(@PathVariable String slug, @PathVariable("postId") long postId, @PathVariable("id") long id, HttpSession session) {
        return changeReplyVote(slug, postId, id, false, session);

    }


    public String changeReplyVote(String slug, long postId, long id, Boolean upvote, HttpSession session) {
        Reply reply = postService.getPostById(postId).get().getReplyById(id).get();
        User user = (User) session.getAttribute("user");

        Voter voter = findVoter(reply, user);

        if(voter == null) {
            voter = new Voter(user, upvote);
            reply.addVote(voter);
            voteService.addVoter(voter);
        }
        else if (voter.isVote() != upvote) {
            voter.setVote(upvote);
        }
        else {
            reply.removeVote(voter);
            voteService.removeVoter(voter);
        }

        replyService.addNewReply(reply);

        return "frontPage.html";
    }

    private Post createPost(String title, Sub sub, String text, MultipartFile image, MultipartFile audio, String recording, HttpSession session) {
        Content content = createContent(text, image, audio, recording);

        User user = (User) session.getAttribute("user");
        if(user != null) return new Post(title,sub,content,user,new ArrayList<Voter>(), new ArrayList<Reply>());

        return new Post(title, sub, content, userService.getAnon(), new ArrayList<Voter>(), new ArrayList<Reply>());
    }


    private Reply createReply(String text, MultipartFile image, MultipartFile audio, String recording, HttpSession session) {
        Content content = createContent(text, image, audio, recording);

        User user = (User) session.getAttribute("user");
        if(user != null) return new Reply(content, user, new ArrayList<Voter>(), new ArrayList<Reply>());

        return new Reply(content, userService.getAnon(), new ArrayList<Voter>(), new ArrayList<Reply>());
    }

    private Content createContent(String text, MultipartFile image, MultipartFile audio, String recording) {
        String imgUrl = "";
        String audioUrl = "";
        String recordingUrl = "";
        if (!image.isEmpty()) imgUrl = cloudinaryService.uploadImage(image);
        if (!audio.isEmpty()) audioUrl = cloudinaryService.uploadAudio(audio);
        if (recording.length() != 9) recordingUrl = cloudinaryService.uploadRecording(recording);
        Content c = new Content(text, imgUrl, audioUrl, recordingUrl);
        return c;
    }


    private User getUser() {
        User user = userService.getUsers().get(0);
        return user;
    }

    private Sub getSub() {
        Sub sub = subService.getSubs().get(0);
        return sub;
    }
    public Voter findVoter(Reply reply, User user) {
        List<Voter> voted = reply.getVoted();
        Optional<Voter> voter = voted.stream().filter(v -> v.getUser().getUser_id() == user.getUser_id()).findAny();

        return voter.orElse(null);
    }
}
