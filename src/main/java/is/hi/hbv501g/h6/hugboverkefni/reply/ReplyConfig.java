package is.hi.hbv501g.h6.hugboverkefni.reply;

import is.hi.hbv501g.h6.hugboverkefni.user.UserRepository;
import is.hi.hbv501g.h6.hugboverkefni.util.Content;
import is.hi.hbv501g.h6.hugboverkefni.user.User;
import is.hi.hbv501g.h6.hugboverkefni.util.Voter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;

@Configuration
public class ReplyConfig {

    @Bean
    CommandLineRunner commandLineReplyRunner(ReplyRepository replyRepository, UserRepository userRepository) {
        return args -> {
            LocalDate date = LocalDate.now();
            Content content = new Content("Fyrsta comment", "mynd.jpg", "hljod.wav", date);
            User user = new User(1L, "gervinotandi2", "gervikona", "mynd.jpg", "net@fa.ng", new ArrayList<Integer>(), date);
            Reply Joseph = new Reply(
                    content,
                    user,
                    new ArrayList<Voter>(),
                    new ArrayList<Long>(),
                    date
            );
            userRepository.save(user);
            replyRepository.save(Joseph);
        };
    }
}

