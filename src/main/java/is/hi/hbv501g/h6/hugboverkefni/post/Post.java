package is.hi.hbv501g.h6.hugboverkefni.post;

import is.hi.hbv501g.h6.hugboverkefni.superClasses.Content;
import is.hi.hbv501g.h6.hugboverkefni.superClasses.Message;
import is.hi.hbv501g.h6.hugboverkefni.user.User;

import javax.persistence.*;

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
    private Long id;
    private String title;

    private Integer sub;

    public Post() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSub() {
        return sub;
    }

    public void setSub(Integer sub) {
        this.sub = sub;
    }
}

