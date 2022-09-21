package is.hi.hbv501g.h6.hugboverkefni.util;

import is.hi.hbv501g.h6.hugboverkefni.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class Message {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "content_id")
    private Content content;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User creator;

    @Transient
    private Integer vote;

    @ElementCollection
    private List<Voter> voted = new ArrayList<>();

    @ElementCollection
    private List<Integer> replies = new ArrayList<>();

    private LocalDate created;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public List<Integer> getReplies() {
        return replies;
    }

    public void setReplies(List<Integer> replies) {
        this.replies = replies;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getVote() {
        int votes = 0;
        while (this.getVoted().iterator().hasNext())
            if (this.getVoted().iterator().next().isVote()) {
                votes++;
            } else {
                votes--;
            }
        return votes;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public List<Voter> getVoted() {
        return voted;
    }

    public void setVoted(List<Voter> voted) {
        this.voted = voted;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
