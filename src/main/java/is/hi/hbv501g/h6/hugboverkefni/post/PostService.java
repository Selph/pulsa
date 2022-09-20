package is.hi.hbv501g.h6.hugboverkefni.post;

import is.hi.hbv501g.h6.hugboverkefni.user.User;

import java.util.List;

public class PostService {
    public List<Post> getPosts() {
        return List.of(
                new Post("Titill",
                        new Content("Texti", "Mynd.jpg", "Hljodbutur.wav"),
                        new User("Gervinotandi", 1, "Mynd.jpg")
                )
        );
    }
}
