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
    private String username;
    private Long userID;
    private boolean vote;

    public Voter(String user, Long userID, boolean vote) {
        this.username = user;
        this.userID = userID;
        this.vote = vote;
    }

    public Voter() {
    }

    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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
                "user='" + username + '\'' +
                ", userID=" + userID +
                ", vote=" + vote +
                '}';
    }
}
