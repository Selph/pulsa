package is.hi.hbv501g.h6.hugboverkefni.sub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/sub")
public class SubController {

    private final SubService subService;

    @Autowired
    public SubController(SubService subService) {
        this.subService = subService;
    }

    @GetMapping
    public List<Sub> getSubs() {
        return subService.getSubs();
    }

    @PostMapping
    public void registerNewSub(@RequestBody Sub sub) {
        subService.addNewSub(sub);
    }

    @DeleteMapping(path = "{subId}")
    public void deleteSub(@PathVariable("subId") Long subId) {
        subService.deleteSub(subId);
    }
}
