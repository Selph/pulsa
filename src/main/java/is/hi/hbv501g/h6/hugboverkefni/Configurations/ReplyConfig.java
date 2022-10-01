package is.hi.hbv501g.h6.hugboverkefni.Configurations;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.*;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.ReplyRepository;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class ReplyConfig {

    @Bean
    CommandLineRunner commandLineReplyRunner(ReplyRepository replyRepository, UserRepository userRepository) {
        return args -> {
            Content content = new Content("Fyrsta comment", "mynd.jpg", "hljod.wav");
            User user = new User(1L,
                    "gervinotandi2",
                    "gervikona",
                    "mynd.jpg",
                    "net@fa.ng",
                    new ArrayList<Sub>(),
                    new ArrayList<Post>(),
                    new ArrayList<Reply>());
            Reply Joseph = new Reply(
                    content,
                    user,
                    new ArrayList<Voter>(),
                    new ArrayList<Reply>()
            );
            userRepository.save(user);
            replyRepository.save(Joseph);
        };
    }
}

