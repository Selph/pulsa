package is.hi.hbv501g.h6.hugboverkefni.Configurations;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.*;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.PostRepository;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;

@Configuration
public class PostConfig {

    @Bean
    CommandLineRunner commandLineRunner(PostRepository postRepository, UserRepository userRepository) {
        return args -> {
            LocalDate date = LocalDate.now();
            Content content = new Content("Hallo Heimur", "mynd.jpg", "hljod.wav", date);
            User user = new User(1L, "gervinotandi1", "gervimadur", "mynd.jpg", "net@fa.ng", new ArrayList<Integer>(), date);
            Post Jonathan = new Post(
                    "Test titill",
                    (long)1,
                    content,
                    user,
                    new ArrayList<Voter>(),
                    new ArrayList<Reply>(),
                    date
            );
            userRepository.save(user);
            postRepository.save(Jonathan);
        };
    }
}
