package is.hi.hbv501g.h6.hugboverkefni.Services;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities.Sub;
import is.hi.hbv501g.h6.hugboverkefni.Persistence.Repositories.SubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubService {
    List<Sub> getSubs();
    Sub getSubById(Long subId);
    Sub addNewSub(Sub sub);
    void deleteSub(Sub sub);
    Sub editSub(Sub sub);
}
