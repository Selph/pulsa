package is.hi.hbv501g.h6.hugboverkefni.post;

import is.hi.hbv501g.h6.hugboverkefni.util.Content;
import is.hi.hbv501g.h6.hugboverkefni.util.Message;
import is.hi.hbv501g.h6.hugboverkefni.user.User;
import is.hi.hbv501g.h6.hugboverkefni.util.Voter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Post extends Message {
    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    private Long postId;
    private String title;

    private Integer sub;

    public Post() {}

    public Post(String title,
                Integer sub,
                Content content,
                User creator,
                List<Voter> voted,
                List<Integer> replies,
                LocalDate created) {
        this.title = title;
        this.sub = sub;
        this.setContent(content);
        this.setCreator(creator);
        this.setVoted(voted);
        this.setReplies(replies);
        this.setCreated(created);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long id) {
        this.postId = id;
    }

    public Integer getSub() {
        return sub;
    }

    public void setSub(Integer sub) {
        this.sub = sub;
    }
}

