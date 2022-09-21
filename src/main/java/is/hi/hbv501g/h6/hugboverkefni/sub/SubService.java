package is.hi.hbv501g.h6.hugboverkefni.sub;

import is.hi.hbv501g.h6.hugboverkefni.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SubService {

    private final SubRepository subRepository;

    @Autowired
    public SubService(SubRepository subRepository) {
        this.subRepository = subRepository;
    }

    public List<Sub> getSubs() {
        return subRepository.findAll();
    }

    public void addNewSub(Sub sub) {
        if (sub.getName().isEmpty())
        {
            throw new IllegalStateException("Sub has to have a name");
        }
        // Vantar hér -> Find by sub name, engin duplicates leyfð. Sjá UserService
        subRepository.save(sub);
    }

    public void deleteSub(Long subId) {
        boolean exists = subRepository.existsById(subId);
        if (!exists) {
            throw new IllegalStateException("sub with id " + subId + " does not exist");
        }
        subRepository.deleteById(subId);
    }
}
