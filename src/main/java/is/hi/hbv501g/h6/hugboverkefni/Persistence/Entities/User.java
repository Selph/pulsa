package is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Embeddable
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long user_id;
    private String userName;
    private String realName;
    private String avatar;
    private String email;

    @ElementCollection
    private List<Sub> subs = new ArrayList<Sub>();

    @ElementCollection
    private List<Post> posts = new ArrayList<Post>();

    @ElementCollection
    private List<Reply> replies = new ArrayList<Reply>();
    private LocalDateTime created;
    private LocalDateTime updated;

    public User() {
    }

    public User(Long user_id,
                String userName,
                String realName,
                String avatar,
                String email,
                List<Sub> subs,
                List<Post> posts,
                List<Reply> replies) {
        this.user_id = user_id;
        this.userName = userName;
        this.realName = realName;
        this.avatar = avatar;
        this.email = email;
        this.subs = subs;
        this.posts = posts;
        this.replies = replies;
        this.setCreated();
        this.setUpdated();
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long id) {
        this.user_id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated() {
        this.created = LocalDateTime.now();
    }

    public List<Sub> getSubs() {
        return subs;
    }

    public void setSubs(List<Sub> subs) {
        this.subs = subs;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated() {
        this.updated = LocalDateTime.now();
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
