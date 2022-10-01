package is.hi.hbv501g.h6.hugboverkefni.Configurations;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Sub;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.SubRepository;
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

