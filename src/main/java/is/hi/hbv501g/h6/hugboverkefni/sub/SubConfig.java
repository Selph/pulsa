package is.hi.hbv501g.h6.hugboverkefni.sub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SubConfig {

    @Bean
    CommandLineRunner commandLineSubRunner(SubRepository subRepository) {
        return args -> {
            Sub graenmeti = new Sub("Graenmeti");
            Sub malning = new Sub("Málning Þornar");
            Sub haskolalifid = new Sub("Háskólalífið");
            subRepository.saveAll(List.of(graenmeti, malning, haskolalifid));
        };
    }
}

