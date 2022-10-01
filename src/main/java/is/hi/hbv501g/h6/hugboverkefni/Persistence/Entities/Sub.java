package is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Sub {
    @Id
    @SequenceGenerator(
            name = "sub_sequence",
            sequenceName = "sub_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sub_sequence"
    )

    private Long subId;
    private String name;

    private String slug;
    @ElementCollection
    private List<User> followers = new ArrayList<>();
    private Integer followerCount;

    public Sub() {
    }

    public Sub(String name) {
        this.name = name;
    }

    public Long getId() {
        return subId;
    }

    public void setId(Long id) {
        this.subId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
