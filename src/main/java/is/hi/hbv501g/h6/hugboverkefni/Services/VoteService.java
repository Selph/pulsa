package is.hi.hbv501g.h6.hugboverkefni.Services;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Voter;
import org.springframework.stereotype.Service;

@Service
public interface VoteService {
    Voter addVoter(Voter vote);

    void removeVoter(Voter vote);
}
