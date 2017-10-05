package be.phury.app.combo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Phury
 */
@RestController
public class SystemController {

    @Autowired private ComboRepository repository;

    @RequestMapping(path = "/")
    public String index() {
        return "Combo server, v0.1";
    }

    @RequestMapping(path = "/ping")
    public String ping() {
        return "pong";
    }

}
