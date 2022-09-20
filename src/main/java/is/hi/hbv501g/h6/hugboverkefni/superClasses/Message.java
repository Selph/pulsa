package is.hi.hbv501g.h6.hugboverkefni.superClasses;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import is.hi.hbv501g.h6.hugboverkefni.superClasses.Content;
import is.hi.hbv501g.h6.hugboverkefni.user.User;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.json.JSONObject;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@MappedSuperclass
@TypeDef(name = "json", typeClass = JsonType.class)
@TypeDef(name = "int-array", typeClass = IntArrayType.class)
public abstract class Message {

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;
    @ManyToOne
    @JoinColumn(name = "creator_user_id")
    private User creator;

    private Integer vote;

    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private JSONObject voted;

    @Type(type = "int-array")
    @Column(columnDefinition = "integer[]")
    private int[] replies;

    private LocalDate created;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public int[] getReplies() {
        return replies;
    }

    public void setReplies(int[] replies) {
        this.replies = replies;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public JSONObject getVoted() {
        return voted;
    }

    public void setVoted(JSONObject voted) {
        this.voted = voted;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
