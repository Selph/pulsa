package is.hi.hbv501g.h6.hugboverkefni.Services;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Reply;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReplyService {
    List<Reply> getReplies();

    Optional<Reply> getReplyById(Long replyId);

    List<Reply> getRepliesByUser(User user);

    Reply addNewReply(Reply reply);

    void deleteReply(Reply reply);

    Reply editReply(Reply reply);
}
