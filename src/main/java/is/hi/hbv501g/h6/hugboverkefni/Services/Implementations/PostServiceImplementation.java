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

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByUser(User user) {
        return postRepository.findByCreator(user);
    }

    @Override
    public Post editPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getPostsOrderedByCreated() {
        return postRepository.findAllByOrderByCreatedDesc();
    }

    @Override
    public List<Post> getSubPostsOrderedByCreated(Sub sub) {
        return postRepository.findAllBySubContainingOrderByCreatedDesc(sub);
    }

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

    public void deletePost(Post post) {
        boolean exists = postRepository.existsById(post.getPostId());
        if (!exists) {
            throw new IllegalStateException("post with id " + post.getPostId() + " does not exist");
        }
        postRepository.delete(post);
    }

    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }
}
