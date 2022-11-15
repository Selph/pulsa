package is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.MappedSuperclass.Message;

import javax.persistence.*;
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



    public Post() {
    }

    /**
     * Post entity
     *
     * @param title   String title of post
     * @param sub     Sub SubPulsa of post
     * @param content Content contains content of post
     * @param creator User Post owner
     * @param voted   List<Voter> List of users that have voted on post
     * @param replies List<Reply> List of replies to post
     * @return Post object
     */
    public Post(String title,
                Sub sub,
                Content content,
                User creator,
                List<Voter> voted,
                List<Reply> replies) {
        this.title = title;
        this.setSub(sub);
        this.setContent(content);
        this.setCreator(creator);
        this.setVoted(voted);
        this.setReplies(replies);
        this.setCreated();
        this.setUpdated();
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


}

