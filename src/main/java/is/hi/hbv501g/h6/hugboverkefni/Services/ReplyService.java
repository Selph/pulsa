package is.hi.hbv501g.h6.hugboverkefni.Services;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Reply;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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


    public void deleteReply(Reply reply) {
        boolean exists = replyRepository.existsById(reply.getReplyId());
        if (!exists) {
            throw new IllegalStateException("reply with id " + reply.getReplyId() + " does not exist");
        }
        replyRepository.deleteById(reply.getReplyId());
    }
}
