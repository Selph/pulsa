package is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Voter, Long> {
}

