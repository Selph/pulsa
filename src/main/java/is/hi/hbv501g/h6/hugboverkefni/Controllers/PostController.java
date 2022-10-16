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

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PostController {
    private PostServiceImplementation postService;
    private UserServiceImplementation userService;
    private ReplyServiceImplementation replyService;

    private VoteServiceImplementation voteService;
    private SubServiceImplementation subService;
    private CloudinaryService cloudinaryService;

    @Autowired
    public PostController(PostServiceImplementation postService,
                          UserServiceImplementation userService,
                          ReplyServiceImplementation replyService,
                          VoteServiceImplementation voteService,
                          SubServiceImplementation subService,
                          CloudinaryService cloudinaryService){
        this.postService = postService;
        this.userService = userService;
        this.replyService = replyService;
        this.voteService = voteService;
        this.subService = subService;
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
    public String newPostPOST(@PathVariable String slug, String title, String text, @RequestParam("image") MultipartFile image, @RequestParam("audio") MultipartFile audio, @RequestParam("recording") String recording, Model model){
        Sub sub = subService.getSubBySlug(slug);
        Post newPost = createPost(title, sub, text, image, audio, recording);
        postService.addNewPost(newPost);
        return "redirect:/p/" + slug + '/' + newPost.getPostId();
    }

    @RequestMapping(value = "/p/{slug}/{id}", method = RequestMethod.POST)
    public String replyPost(@PathVariable String slug, @PathVariable("id") long id, String text, @RequestParam("image") MultipartFile image, @RequestParam("audio") MultipartFile audio, @RequestParam("recording") String recording, Model model) {
        Optional<Post> post = postService.getPostById(id);
        if(!post.isPresent()) return "postNotFound";

        Reply r = createReply(text, image, audio, recording);
        replyService.addNewReply(r);
        post.get().addReply(r);
        postService.addNewPost(post.get());

        return "redirect:/p/" + slug + '/' + post.get().getPostId();
    }

    @RequestMapping(value = "/p/{slug}/{postId}/{id}", method = RequestMethod.POST)
    public String replyReply(@PathVariable String slug, @PathVariable("postId") long postId, @PathVariable("id") long id, String text, @RequestParam("image") MultipartFile image, @RequestParam("audio") MultipartFile audio,@RequestParam("recording") String recording, Model model) {
        Optional<Reply> prevReply = replyService.getReplyById(id);
        if(!prevReply.isPresent()) return "postNotFound";

        Reply r = createReply(text, image, audio, recording);
        replyService.addNewReply(r);
        prevReply.get().addReply(r);
        replyService.addNewReply(prevReply.get());

        return "redirect:/p/" + slug + '/' + postId;
    }

    @RequestMapping(value = "/p/{slug}/{postId}/{id}/vote", method = RequestMethod.GET)
    @ResponseBody
    public String getReplyVote(@PathVariable String slug, @PathVariable("postId") long postId, @PathVariable("id") long id, Model model) {
        Reply reply = postService.getPostById(postId).get().getReplyById(id).get();

        return reply.getVote().toString();
    }

    @RequestMapping(value = "/p/{slug}/{postId}/{id}/upvote", method = RequestMethod.POST)
    public String upvoteReply(@PathVariable String slug, @PathVariable("postId") long postId, @PathVariable("id") long id, Model model) {
        Reply reply = postService.getPostById(postId).get().getReplyById(id).get();
        Voter voter = new Voter(userService.getUserByUserName("gervinotandi1").get(), true);
        Voter v = voteService.addVoter(voter);
        reply.addVote(v);
        replyService.addNewReply(reply);

        System.out.println(reply.getVoted());

        return "frontPage.html";
    }

    @RequestMapping(value = "/p/{slug}/{postId}/{id}/downvote", method = RequestMethod.POST)
    public String downvoteReply(@PathVariable String slug, @PathVariable("postId") long postId, @PathVariable("id") long id, Model model) {
        Reply reply = postService.getPostById(postId).get().getReplyById(id).get();
        Voter voter = new Voter(userService.getUserByUserName("gervinotandi1").get(), false);
        reply.addVote(voter);
        replyService.addNewReply(reply);


        return "frontPage.html";
    }

    private Post createPost(String title, Sub sub, String text, MultipartFile image, MultipartFile audio, String recording) {
        Content c = createContent(text, image, audio, recording);
        User user = getUser();
        Post newPost = new Post(title,
                                sub,
                                c,
                                user,
                                new ArrayList<Voter>(),
                                new ArrayList<Reply>());
        return newPost;
    }


    private Reply createReply(String text, MultipartFile image, MultipartFile audio, String recording) {
        Content c = createContent(text, image, audio, recording);
        User user = getUser();
        Reply r = new Reply(c, user, new ArrayList<Voter>(), new ArrayList<Reply>());
        return r;
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
}
