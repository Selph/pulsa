package is.hi.hbv501g.h6.hugboverkefni.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public List<Reply> getReplies() {
        return replyRepository.findAll();
    }

    public void addNewReply(Reply reply) {
        if (reply.getContent().getText().isEmpty() &&
                reply.getContent().getImage().isEmpty() &&
                reply.getContent().getAudio().isEmpty())
        {
            throw new IllegalStateException("Reply has to have text, image or audio");
        }
        replyRepository.save(reply);
    }

    public Optional<Reply> findReplyById(Long id) {
        return replyRepository.findById(id);
    }

    public void deleteReply(Long replyId) {
        boolean exists = replyRepository.existsById(replyId);
        if (!exists) {
            throw new IllegalStateException("reply with id " + replyId + " does not exist");
        }
        replyRepository.deleteById(replyId);
    }
}
