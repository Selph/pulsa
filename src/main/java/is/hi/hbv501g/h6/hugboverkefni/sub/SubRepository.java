package is.hi.hbv501g.h6.hugboverkefni.sub;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRepository extends JpaRepository<Sub, Long> {
}

