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

    /**
     * Returns all replies in database
     * @return List<Reply>
     */
    public List<Reply> getReplies() {
        return replyRepository.findAll();
    }

    /**
     * Returns reply with provided ID if it exists
     * @param replyId Long ID of requested reply
     * @return Optional<Reply>
     */
    @Override
    public Optional<Reply> getReplyById(Long replyId) {
        return replyRepository.findById(replyId);
    }

    /**
     * Returns a list of replies belonging to provided User
     * @param user User is owner of Replies
     * @return List<Reply>
     */
    @Override
    public List<Reply> getRepliesByUser(User user) {
        return replyRepository.findByCreator(user);
    }

    /**
     * Adds Reply to database.
     * Has to contain Content object that is not empty
     * @param reply Reply that will be added to database
     * @return Reply
     */
    public Reply addNewReply(Reply reply) {
        if (reply.getContent().getText().isEmpty() &&
                reply.getContent().getImage().isEmpty() &&
                reply.getContent().getAudio().isEmpty() &&
                reply.getContent().getRecording().isEmpty())
        {
            throw new IllegalStateException("Reply has to have text, image, audio or recording");
        }
        return replyRepository.save(reply);
    }

    /**
     * Returns Reply object with provided ID
     * @param reply Reply contains ID
     * @return Optional<Reply>
     */
    public Optional<Reply> findReplyById(Reply reply) {
        return replyRepository.findById(reply.getReplyId());
    }

    /**
     * Deletes Reply from database
     * @param reply Reply to be deleted
     */
    public void deleteReply(Reply reply) {
        boolean exists = replyRepository.existsById(reply.getReplyId());
        if (!exists) {
            throw new IllegalStateException("reply with id " + reply.getReplyId() + " does not exist");
        }
        replyRepository.deleteById(reply.getReplyId());
    }

    /**
     * Replaces Reply in database
     * @param reply Reply to be replaced
     * @return Reply
     */
    @Override
    public Reply editReply(Reply reply) {
        return replyRepository.save(reply);
    }
}
