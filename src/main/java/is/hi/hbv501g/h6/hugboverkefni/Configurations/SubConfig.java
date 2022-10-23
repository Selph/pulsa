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
            Sub graenmeti = new Sub("Grænmeti");
            graenmeti.setImage("https://img.freepik.com/free-photo/healthy-vegetables-wooden-table_1150-38014.jpg");
            Sub malning = new Sub("Málning Þornar");
            malning.setImage("https://i0.wp.com/www.goingconcern.com/wp-content/uploads/2019/02/Paint-drying.jpg");
            subRepository.saveAll(List.of(graenmeti, malning));
        };
    }
}

