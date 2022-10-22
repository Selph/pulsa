package is.hi.hbv501g.h6.hugboverkefni.Services.Implementations;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Post;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Sub;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.User;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.PostRepository;
import is.hi.hbv501g.h6.hugboverkefni.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImplementation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Returns all posts in database
     *
     * @return List<Post>
     */
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    /**
     * Return all posts database owned by user
     * @param user User object
     * @return List<Post>
     */
    @Override
    public List<Post> getPostsByUser(User user) {
        return postRepository.findByCreator(user);
    }

    /**
     * Replaces Post in database
     * @param post Post that will be replaced
     * @return Post
     */
    @Override
    public Post editPost(Post post) {
        return postRepository.save(post);
    }

    /**
     * Returns all posts ordered by the date they were created, descending
     * @return List<Post>
     */
    @Override
    public List<Post> getPostsOrderedByCreated() {
        return postRepository.findAllByOrderByCreatedDesc();
    }

    /**
     * Returns all posts in particular subpulsa ordered by
     * the date they were created, descending
     * @param sub Sub that posts are related to
     * @return List<Post>
     */
    @Override
    public List<Post> getSubPostsOrderedByCreated(Sub sub) {
        return postRepository.findAllBySubOrderByCreatedDesc(sub);
    }

    /**
     * Adds Post to database.
     * Has to contain title.
     * Has to contain Content that is not empty.
     * @param post Post that will be added to database
     * @return Post
     */
    public Post addNewPost(Post post) {

        if (post.getTitle().isEmpty()) {
            throw new IllegalStateException("no title");
        }
        if (post.getContent().getText().isEmpty() &&
                post.getContent().getImage().isEmpty() &&
                post.getContent().getAudio().isEmpty() &&
                post.getContent().getRecording().isEmpty())
        {
            throw new IllegalStateException("Post has to have text, image, audio or recording");
        }
        return postRepository.save(post);
    }

    /**
     * Deletes Post from database
     * @param post Post that will be removed from database
     */
    public void deletePost(Post post) {
        boolean exists = postRepository.existsById(post.getPostId());
        if (!exists) {
            throw new IllegalStateException("post with id " + post.getPostId() + " does not exist");
        }
        postRepository.delete(post);
    }

    /**
     * Returns Post object with provided ID
     * @param postId Long ID of post requested if it exists
     * @return Optional<Post>
     */
    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }
}
