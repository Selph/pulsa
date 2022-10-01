package is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.MappedSuperclass.Message;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Embeddable
public class Reply extends Message {
    @Id
    @SequenceGenerator(
            name = "reply_sequence",
            sequenceName = "reply_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reply_sequence"
    )
    private Long replyId;
    public Reply(Content content, User user, List<Voter> voted, List<Reply> replies) {
        this.setContent(content);
        this.setCreator(user);
        this.setVoted(voted);
        this.setReplies(replies);
        this.setCreated();
        this.setUpdated();

    }

    public Reply() {
    }

    public Long getReplyId() {
        return this.replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }
}
