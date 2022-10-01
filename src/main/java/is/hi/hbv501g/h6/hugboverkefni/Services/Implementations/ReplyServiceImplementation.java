package is.hi.hbv501g.h6.hugboverkefni.Services.Implementations;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Reply;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.User;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.ReplyRepository;
import is.hi.hbv501g.h6.hugboverkefni.Services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyServiceImplementation implements ReplyService {

    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyServiceImplementation(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public List<Reply> getReplies() {
        return replyRepository.findAll();
    }

    @Override
    public Optional<Reply> getReplyById(Long replyId) {
        return replyRepository.findById(replyId);
    }

    @Override
    public List<Reply> getRepliesByUser(User user) {
        return replyRepository.findByCreator(user);
    }

    public Reply addNewReply(Reply reply) {
        if (reply.getContent().getText().isEmpty() &&
                reply.getContent().getImage().isEmpty() &&
                reply.getContent().getAudio().isEmpty())
        {
            throw new IllegalStateException("Reply has to have text, image or audio");
        }
        return replyRepository.save(reply);
    }

    public Optional<Reply> findReplyById(Reply reply) {
        return replyRepository.findById(reply.getReplyId());
    }

    public void deleteReply(Reply reply) {
        boolean exists = replyRepository.existsById(reply.getReplyId());
        if (!exists) {
            throw new IllegalStateException("reply with id " + reply.getReplyId() + " does not exist");
        }
        replyRepository.deleteById(reply.getReplyId());
    }

    @Override
    public Reply editReply(Reply reply) {
        return replyRepository.save(reply);
    }
}
