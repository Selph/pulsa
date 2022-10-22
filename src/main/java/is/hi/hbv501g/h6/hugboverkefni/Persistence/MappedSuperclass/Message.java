package is.hi.hbv501g.h6.hugboverkefni.Persistence.MappedSuperclass;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Content;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Reply;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.User;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Voter;
import is.hi.hbv501g.h6.hugboverkefni.Services.UserService;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@MappedSuperclass
public abstract class Message {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "content_id")
    private Content content;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User creator;

    @Transient
    private int Vote;

    @ElementCollection
    private List<Voter> voted = new ArrayList<>();

    @ElementCollection
    private List<Reply> replies = new ArrayList<>();

    private LocalDateTime created;

    private LocalDateTime updated;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public Optional<Reply> getReplyById(Long replyId) {
        System.out.println("Finding " + replyId);
        for (Reply reply : this.replies) {
            System.out.println("Is it " + reply.getReplyId() + "?");
            if (reply.getReplyId() == replyId)
                return Optional.of(reply);
        }

        return Optional.empty();
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public void addReply(Reply reply) { this.replies.add(reply); }
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getVote() {
        int votes = 0;

        for (Voter vote : this.getVoted()) {
            if (vote.isVote()) {
                votes++;
            } else {
                votes--;
            }
    }

        return votes;
    }

    public List<Voter> getVoted() {
        return voted;
    }

    public void setVoted(List<Voter> voted) {
        this.voted = voted;
    }

    public void addVote(Voter voter) {
            this.voted.add(voter);
    }

    public void removeVote(Voter vote) {
        this.voted.remove(vote);
    }

    public LocalDateTime getCreated() {
        return LocalDateTime.now();
    }

    public void setCreated() {
        this.created = LocalDateTime.now();
    }

    public LocalDateTime getUpdated() {
        return LocalDateTime.now();
    }

    public void setUpdated() {
        this.updated = LocalDateTime.now();
    }


}
