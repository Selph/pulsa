package is.hi.hbv501g.h6.hugboverkefni.Configurations;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.*;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class PostConfig {

    @Bean
    CommandLineRunner commandLineRunner(PostRepository postRepository, UserRepository userRepository, ReplyRepository replyRepository) {
        return args -> {
            User anon = new User("Anonymous",
                                "aksjdfefefsdfa",
                                "Anonymous",
                                "https://res.cloudinary.com/dc6h0nrwk/image/upload/v1666386282/xov6nkbsxf3hmhuqb3jn.png",
                                "anon@anon.com");
            userRepository.save(anon);
            Sub sub = new Sub("Háskólalífið");
            sub.setImage("https://res.cloudinary.com/dc6h0nrwk/image/upload/v1665799070/i3g9v3wdjlzbeaxvhihc.jpg");
            LocalDateTime date = LocalDateTime.now();
            Content content = new Content("Hallo Heimur", "http://res.cloudinary.com/dc6h0nrwk/image/upload/v1664770603/q1h9kuthpcgqpkgz8c8r.png", "http://res.cloudinary.com/dc6h0nrwk/video/upload/v1664772263/ltaf63f4ococlisbqtfo.mp3", "http://res.cloudinary.com/dc6h0nrwk/video/upload/v1664826994/m11awfhrnvyfw6uc5crg.ogg");
            User user = new User(
                    "gervinotandi1",
                    "gervi",
                    "gervimadur",
                    "mynd.jpg",
                    "net@fi.ng");;
            Content content2 = new Content("Sugoi!", "http://res.cloudinary.com/dc6h0nrwk/image/upload/v1664806729/uakzskirbbkgb30tejb0.gif", "http://res.cloudinary.com/dc6h0nrwk/video/upload/v1664806897/xzn8cw8bkvv3moc3d8ua.mp3", "");
            User user2 = new User(
                    "gervinotandi3",
                    "gervi",
                    "gervikona",
                    "mynd.jpg",
                    "net@fe.ng");
            User user3 = new User(
                    "test",
                    "test",
                    "test",
                    "mynd.jpg",
                    "test@test.com");
            Reply reply = new Reply(content2, user2, new ArrayList<Voter>(), new ArrayList<Reply>());
            Voter voter = new Voter(user, true);
            userRepository.save(user2);
            replyRepository.save(reply);
            List<Reply> replies = new ArrayList<Reply>();
            replies.add(0, reply);
            Post Jonathan = new Post(
                    "Test titill",
                    sub,
                    content,
                    user,
                    new ArrayList<Voter>(),
                    replies
            );

            userRepository.save(user);
            userRepository.save(user3);
            postRepository.save(Jonathan);
        };
    }
}
