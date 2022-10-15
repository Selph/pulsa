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

    public List<Sub> getSubs() {
        return subRepository.findAll();
    }

    @Override
    public Sub getSubById(Long subId) {
        return null;
    }

    public Sub addNewSub(Sub sub) {
        if (sub.getName().isEmpty())
        {
            throw new IllegalStateException("Sub has to have a name");
        }
        // Vantar hér -> Find by sub name, engin duplicates leyfð. Sjá UserService
        return subRepository.save(sub);
    }

    @Override
    public Sub editSub(Sub sub) {
        return null;
    }

    @Override
    public Sub getSubBySlug(String slug) {
        return subRepository.findBySlug(slug);
    }

    public void deleteSub(Sub sub) {
        boolean exists = subRepository.existsById(sub.getSub_id());
        if (!exists) {
            throw new IllegalStateException("sub with id " + sub.getSub_id() + " does not exist");
        }
        subRepository.delete(sub);
    }
}
