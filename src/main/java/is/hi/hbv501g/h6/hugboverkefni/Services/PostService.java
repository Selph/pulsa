package is.hi.hbv501g.h6.hugboverkefni.Services;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Post;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Sub;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PostService {
    Post addNewPost(Post post);

    void deletePost(Post post);

    List<Post> getPosts();

    Optional<Post> getPostById(Long postId);

    List<Post> getPostsByUser(User user);

    Post editPost(Post post);

    List<Post> getPostsOrderedByCreated();

    List<Post> getSubPostsOrderedByCreated(Sub sub);

}
