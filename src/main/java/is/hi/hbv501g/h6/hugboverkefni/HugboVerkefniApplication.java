package is.hi.hbv501g.h6.hugboverkefni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// This is team member Abel
// This is team member Gylfi
// This is team member Daníel
@SpringBootApplication
@RestController
public class HugboVerkefniApplication {

    public static void main(String[] args) {
        SpringApplication.run(HugboVerkefniApplication.class, args);
    }

    @GetMapping("")
    public String start() {
        return "Hello world";
    }
}
