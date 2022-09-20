package is.hi.hbv501g.h6.hugboverkefni.reply;

import is.hi.hbv501g.h6.hugboverkefni.superClasses.Content;
import is.hi.hbv501g.h6.hugboverkefni.superClasses.Message;
import is.hi.hbv501g.h6.hugboverkefni.user.User;

import javax.persistence.*;

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
    private Integer id;
    public Reply(Content content, User user) {
        this.setContent(content);
        this.setCreator(user);
    }

    public Reply() {
    }
}
