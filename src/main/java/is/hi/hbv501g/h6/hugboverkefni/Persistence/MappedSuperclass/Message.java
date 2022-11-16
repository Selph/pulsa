package is.hi.hbv501g.h6.hugboverkefni.Persistence.MappedSuperclass;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "sub_id")
    private Sub sub;

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

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public Sub getSub() {
        return sub;
    }

    public void setSub(Sub sub) {
        this.sub = sub;
    }

    /**
     * Returns internal Reply object by id if it exists
     *
     * @param replyId Long id of Reply
     * @return Optional<Reply>
     */
    public Optional<Reply> getReplyById(Long replyId) {
        System.out.println("Finding " + replyId);
        for (Reply reply : this.replies) {
            System.out.println("Is it " + reply.getReplyId() + "?");
            if (reply.getReplyId() == replyId)
                return Optional.of(reply);
        }

        return Optional.empty();
    }

    public void addReply(Reply reply) {
        this.replies.add(reply);
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * Returns sum of total upvotes and downvotes on Message
     * Voter object contains boolean value "vote"
     * Upvote if true, votes is incremented
     * Downvote if false, votes is decremented
     *
     * @return Integer Sum of total votes on Message
     */
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

    public Optional<Voter> findVoter(User user) {
        List<Voter> voted = this.getVoted();
        Optional<Voter> voter = voted.stream().filter(v -> v.getUser().getUser_id() == user.getUser_id()).findAny();
        return voter;
    }


}
