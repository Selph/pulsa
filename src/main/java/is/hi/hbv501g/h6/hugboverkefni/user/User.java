package is.hi.hbv501g.h6.hugboverkefni.user;

import javax.persistence.*;
import java.time.LocalDate;
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
    private List<Integer> subs = new ArrayList<Integer>();
    private LocalDate created;

    public User() {
    }

    public User(Long user_id, String userName, String realName, String avatar, String email, List<Integer> subs, LocalDate created) {
        this.user_id = user_id;
        this.userName = userName;
        this.realName = realName;
        this.avatar = avatar;
        this.email = email;
        this.subs = subs;
        this.created = created;
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

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
