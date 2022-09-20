package is.hi.hbv501g.h6.hugboverkefni.sub;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import is.hi.hbv501g.h6.hugboverkefni.superClasses.Message;
import is.hi.hbv501g.h6.hugboverkefni.user.User;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table
@TypeDef(name = "json", typeClass = JsonType.class)
@TypeDef(name = "int-array", typeClass = IntArrayType.class)
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

    private Long id;
    private String name;
    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private ArrayList<User> followers;
    private Integer followerCount;

    public Sub() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }
}
