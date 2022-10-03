package is.hi.hbv501g.h6.hugboverkefni.Controllers;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.*;
import is.hi.hbv501g.h6.hugboverkefni.Services.CloudinaryService;
import is.hi.hbv501g.h6.hugboverkefni.Services.Implementations.PostServiceImplementation;
import is.hi.hbv501g.h6.hugboverkefni.Services.Implementations.ReplyServiceImplementation;
import is.hi.hbv501g.h6.hugboverkefni.Services.Implementations.SubServiceImplementation;
import is.hi.hbv501g.h6.hugboverkefni.Services.Implementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PostController {
    private PostServiceImplementation postService;
    private UserServiceImplementation userService;
    private ReplyServiceImplementation replyService;
    private SubServiceImplementation subService;
    private CloudinaryService cloudinaryService;

    @Autowired
    public PostController(PostServiceImplementation postService,
                          UserServiceImplementation userService,
                          ReplyServiceImplementation replyService,
                          SubServiceImplementation subService,
                          CloudinaryService cloudinaryService){
        this.postService = postService;
        this.userService = userService;
        this.replyService = replyService;
        this.subService = subService;
        this.cloudinaryService = cloudinaryService;
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.GET)
    public String newPostGET(Post post){

        return "newPost";
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public String newPostPOST(String title, String text, @RequestParam("image") MultipartFile image, @RequestParam("audio") MultipartFile audio, @RequestParam("recording") String recording, Model model){
        String imgUrl = "";
        String audioUrl = "";
        String recordingUrl = "";
        if (!image.isEmpty()) imgUrl = cloudinaryService.uploadImage(image);
        if (!audio.isEmpty()) audioUrl = cloudinaryService.uploadAudio(audio);
        if (recording.length() != 9) recordingUrl = cloudinaryService.uploadRecording(recording);
        Content c = new Content(text, imgUrl, audioUrl, recordingUrl);
        User user = userService.getUsers().get(0);
        Sub sub = subService.getSubs().get(0);
        Post newPost = new Post(title,
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
        Optional<Post> post = postService.getPostById(id);
        if(!post.isPresent()) return "postNotFound";

        model.addAttribute("post", post.get());
        model.addAttribute("postReplies", post.get().getReplies());
        model.addAttribute("reply", new Reply());
        model.addAttribute("content", new Content());
        return "postPage";
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.POST)
    public String replyPost(@PathVariable("id") long id, String text, @RequestParam("image") MultipartFile image, @RequestParam("audio") MultipartFile audio, @RequestParam("recording") String recording, Model model) {
        Optional<Post> post = postService.getPostById(id);
        if(!post.isPresent()) return "postNotFound";

        String imgUrl = "";
        String audioUrl = "";
        String recordingUrl = "";
        System.out.println(recording.length());
        if (!image.isEmpty()) imgUrl = cloudinaryService.uploadImage(image);
        if (!audio.isEmpty()) audioUrl = cloudinaryService.uploadAudio(audio);
        if (recording.length() != 9) recordingUrl = cloudinaryService.uploadRecording(recording);
        Content c = new Content(text, imgUrl, audioUrl, recordingUrl);
        User user = userService.getUsers().get(0);
        Reply r = new Reply(c, user, new ArrayList<Voter>(), new ArrayList<Reply>());
        replyService.addNewReply(r);
        post.get().addReply(r);
        postService.addNewPost(post.get());

        return "redirect:/post/" + post.get().getPostId();
    }

    @RequestMapping(value = "/post/{postId}/{id}", method = RequestMethod.POST)
    public String replyReply(@PathVariable("postId") long postId, @PathVariable("id") long id, String text, @RequestParam("image") MultipartFile image, @RequestParam("audio") MultipartFile audio,@RequestParam("recording") String recording, Model model) {
        Optional<Reply> prevReply = replyService.getReplyById(id);
        if(!prevReply.isPresent()) return "postNotFound";

        String imgUrl = "";
        String audioUrl = "";
        String recordingUrl = "";
        if (!image.isEmpty()) imgUrl = cloudinaryService.uploadImage(image);
        if (!audio.isEmpty()) audioUrl = cloudinaryService.uploadAudio(audio);
        if (recording.length() != 9) recordingUrl = cloudinaryService.uploadRecording(recording);
        Content c = new Content(text, imgUrl, audioUrl, recordingUrl);
        User user = userService.getUsers().get(0);
        Reply r = new Reply(c, user, new ArrayList<Voter>(), new ArrayList<Reply>());
        replyService.addNewReply(r);
        prevReply.get().addReply(r);
        replyService.addNewReply(prevReply.get());

        return "redirect:/post/" + postId;
    }
}
