package is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities;

import com.github.slugify.Slugify;

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

    private Long sub_id;
    private String name;

    @Column(unique = true)
    private String slug;
    @ElementCollection
    private List<User> followers = new ArrayList<>();
    private String image;
    private Integer followerCount;

    public Sub() {
    }

    /**
     * Sub entity
     *
     * @param name String name of subpulsa
     * @return Sub
     */
    public Sub(String name) {
        this.name = name;
        this.slug = createSlug(name);
    }

    /*
    Creates a unique identifying subpulsa name for its URL
     */
    private String createSlug(String name) {
        Slugify slg = Slugify.builder().build();
        String slug = slg.slugify(name);
        return slug;
    }

    public Long getId() {
        return sub_id;
    }

    public void setId(Long id) {
        this.sub_id = id;
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

    public Long getSub_id() {
        return sub_id;
    }

    public void setSub_id(Long subId) {
        this.sub_id = subId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
