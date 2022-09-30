package is.hi.hbv501g.h6.hugboverkefni.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public void addNewPost(Post post) {

        if (post.getTitle().isEmpty()) {
            throw new IllegalStateException("no title");
        }
        if (post.getContent().getText().isEmpty() &&
                post.getContent().getImage().isEmpty() &&
                post.getContent().getAudio().isEmpty())
        {
            throw new IllegalStateException("Post has to have text, image or audio");
        }
        postRepository.save(post);
    }

    public void deletePost(Long postId) {
        boolean exists = postRepository.existsById(postId);
        if (!exists) {
            throw new IllegalStateException("post with id " + postId + " does not exist");
        }
        postRepository.deleteById(postId);
    }

    public Optional<Post> findPostById(Long postId) {
        return postRepository.findById(postId);
    }
}
