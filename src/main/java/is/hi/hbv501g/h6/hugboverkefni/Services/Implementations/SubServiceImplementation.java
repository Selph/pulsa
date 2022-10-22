package is.hi.hbv501g.h6.hugboverkefni.Services.Implementations;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Sub;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.SubRepository;
import is.hi.hbv501g.h6.hugboverkefni.Services.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubServiceImplementation implements SubService {

    private final SubRepository subRepository;

    @Autowired
    public SubServiceImplementation(SubRepository subRepository) {
        this.subRepository = subRepository;
    }

    /**
     * Returns all Subs
     * @return List<Sub>
     */
    public List<Sub> getSubs() {
        return subRepository.findAll();
    }

    /**
     * Returns sub from ID
     * @param subId Long ID of subpulsa
     * @return Sub
     */
    @Override
    public Sub getSubById(Long subId) {
        return null;
    }

    /**
     * Adds Sub to database if it has a name
     * @param sub Sub to be added to database
     * @return Sub
     */
    public Sub addNewSub(Sub sub) {
        if (sub.getName().isEmpty())
        {
            throw new IllegalStateException("Sub has to have a name");
        }
        // Vantar hér -> Find by sub name, engin duplicates leyfð. Sjá UserService
        return subRepository.save(sub);
    }

    /**
     * Edits provided sub if it exists
     * @param sub Sub to be edited
     * @return Sub
     */
    @Override
    public Sub editSub(Sub sub) {
        return null;
    }

    /**
     * Returns Sub with provided Slug identifier
     * @param slug String slug belonging to particular sub
     * @return Sub
     */
    @Override
    public Sub getSubBySlug(String slug) {
        return subRepository.findBySlug(slug);
    }

    /**
     * Deletes provided Sub from database
     * @param sub Sub to be deleted
     */
    public void deleteSub(Sub sub) {
        boolean exists = subRepository.existsById(sub.getSub_id());
        if (!exists) {
            throw new IllegalStateException("sub with id " + sub.getSub_id() + " does not exist");
        }
        subRepository.delete(sub);
    }
}
