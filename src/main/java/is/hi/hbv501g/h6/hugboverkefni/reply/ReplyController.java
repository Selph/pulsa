package is.hi.hbv501g.h6.hugboverkefni.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/reply")
public class ReplyController {

    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping
    public List<Reply> getReplys() {
        return replyService.getReplies();
    }

    @PostMapping
    public void registerNewReply(@RequestBody Reply reply) {
        replyService.addNewReply(reply);
    }

    @DeleteMapping(path = "{replyId}")
    public void deleteReply(@PathVariable("replyId") Long replyId) {
        replyService.deleteReply(replyId);
    }
}
