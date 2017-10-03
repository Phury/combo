package be.phury.app.combo;

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

    private List<Combo> repository = Arrays.asList(
        new Combo(){{
            setName("draw 10 minutes");
            setHits(42);
            setHitsLimit(31);
        }},
        new Combo(){{
            setName("drink 5 glasses of water a day");
            setHits(16);
            setHitsLimit(3);
        }},
        new Combo(){{
            setName("do 100 suburi");
            setHits(2);
            setHitsLimit(31);
        }},
        new Combo(){{
            setName("go to fitness");
            setHits(10);
            setHitsLimit(7);
        }}
    );

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
