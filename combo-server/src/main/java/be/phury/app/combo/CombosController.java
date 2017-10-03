package be.phury.app.combo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Phury
 */
@RestController
public class CombosController {

    @Autowired private ComboRepository repository;

    @RequestMapping(path = "/")
    public String index() {
        return "Combo server, v0.1";
    }

    @RequestMapping(path = "/combo")
    public List<Combo> comboList() {
        return repository.list();
    }

    @RequestMapping(path = "/combo/{comboId}", method = RequestMethod.POST)
    public Combo updateCombo(@PathVariable String comboId, @RequestBody Combo combo) {
        return repository.update(combo);
    }
}
