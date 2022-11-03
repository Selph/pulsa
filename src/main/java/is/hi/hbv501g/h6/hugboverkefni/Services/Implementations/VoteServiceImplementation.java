package is.hi.hbv501g.h6.hugboverkefni.Services.Implementations;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Voter;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.VoteRepository;
import is.hi.hbv501g.h6.hugboverkefni.Services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImplementation implements VoteService {
    private final VoteRepository voteRepository;

    @Autowired
    public VoteServiceImplementation(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    /**
     * Adds Voter to database
     *
     * @param vote Voter object to be added
     * @return Voter
     */
    public Voter addVoter(Voter vote) {
        return voteRepository.save(vote);
    }

    /**
     * Removes Voter object from database
     *
     * @param vote Voter Object to be removed
     */
    public void removeVoter(Voter vote) {
        voteRepository.delete(vote);
    }
}
