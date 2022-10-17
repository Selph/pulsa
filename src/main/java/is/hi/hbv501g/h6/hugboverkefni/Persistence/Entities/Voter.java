package is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities;

import javax.persistence.*;

@Embeddable
@Entity
@Table
public class Voter {
    @Id
    @SequenceGenerator(
            name = "voter_sequence",
            sequenceName = "voter_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "voter_sequence"
    )
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    private boolean vote;

    public Voter(User user, boolean vote) {
        this.user = user;
        this.vote = vote;
    }

    public Voter() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(String username) {
        this.user = user;
    }

    public Long getUserID() {
        return user.getUser_id();
    }

    public void setUserID(Long userID) {
        this.user.setUser_id(userID);
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "Voter{" +
                "id='" + id + '\'' +
                "user='" + user + '\'' +
                ", userID=" + user.getUser_id()+
                ", vote=" + vote +
                '}';
    }
}
