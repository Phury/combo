package be.phury.app.combo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Phury
 */
@Service
public class ComboRepository {

    @Autowired
    private List<Combo> repository;

    public List<Combo> list() {
        return Collections.unmodifiableList(repository);
    }

    public Combo getElementById(String id) {
        return repository
                .stream()
                .filter(combo -> id.equals(combo.getId()))
                .collect(Collectors.toList())
                .get(0);
    }

    public Combo update(Combo toUpdate) {
        if (getElementById(toUpdate.getId()) == null) throw new IllegalArgumentException("No combo with id " + toUpdate.getId());
        toUpdate.setLastModificationDate(new Date().getTime());

        repository = repository
                .stream()
                .map(combo -> toUpdate.getId().equals(combo.getId()) ? toUpdate : combo)
                .collect(Collectors.toList());

        return toUpdate;
    }
}
