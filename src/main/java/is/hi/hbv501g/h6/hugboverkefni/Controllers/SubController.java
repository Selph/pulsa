package is.hi.hbv501g.h6.hugboverkefni.Controllers;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Post;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Sub;
import is.hi.hbv501g.h6.hugboverkefni.Services.CloudinaryService;
import is.hi.hbv501g.h6.hugboverkefni.Services.Implementations.PostServiceImplementation;
import is.hi.hbv501g.h6.hugboverkefni.Services.Implementations.SubServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class SubController {

    private final PostServiceImplementation postService;
    private final SubServiceImplementation subService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public SubController(PostServiceImplementation postService,
                         SubServiceImplementation subService,
                         CloudinaryService cloudinaryService) {
        this.postService = postService;
        this.subService = subService;
        this.cloudinaryService = cloudinaryService;
    }

    @RequestMapping(value = "/p/", method = RequestMethod.GET)
    public String subIndex(Model model) {
        List<Sub> allSubs = subService.getSubs();

        model.addAttribute("subs", allSubs);

        return "subIndex";
    }

    @RequestMapping(value = "/p/{slug}", method = RequestMethod.GET)
    public String subPage(@PathVariable("slug") String slug, Model model) {
        Sub sub = subService.getSubBySlug(slug);
        List<Post> posts = postService.getSubPostsOrderedByCreated(sub);

        model.addAttribute("sub", sub);
        model.addAttribute("posts", posts);

        return "subPage";
    }

    @RequestMapping(value = "/newSub", method = RequestMethod.GET)
    public String newSubGET(Sub sub) {

        return "newSub";
    }

    @RequestMapping(value = "/newSub", method = RequestMethod.POST)
    public String newSubPOST(String name, @RequestParam("image") MultipartFile image, Model model) {
        Sub newSub = new Sub(name);
        System.out.println(subService.getSubBySlug(newSub.getSlug()));
        if (subService.getSubBySlug(newSub.getSlug()) != null) return "subNameDuplicate";
        String imgUrl = "";
        if (!image.isEmpty()) imgUrl = cloudinaryService.uploadImage(image);
        newSub.setImage(imgUrl);
        subService.addNewSub(newSub);
        return "redirect:/p/" + newSub.getSlug();
    }

}
