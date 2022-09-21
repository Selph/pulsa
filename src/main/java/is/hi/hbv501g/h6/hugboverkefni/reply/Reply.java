package is.hi.hbv501g.h6.hugboverkefni.reply;

import is.hi.hbv501g.h6.hugboverkefni.util.Content;
import is.hi.hbv501g.h6.hugboverkefni.util.Message;
import is.hi.hbv501g.h6.hugboverkefni.user.User;
import is.hi.hbv501g.h6.hugboverkefni.util.Voter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
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
    private Integer replyId;
    public Reply(Content content, User user, List<Voter> voted, List<Integer> replies, LocalDate date) {
        this.setContent(content);
        this.setCreator(user);
        this.setVoted(voted);
        this.setReplies(replies);
        this.setCreated(date);

    }

    public Reply() {
    }
}
