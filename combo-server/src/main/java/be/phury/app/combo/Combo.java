package be.phury.app.combo;

import java.util.Date;

/**
 * Created by Phury
 */
public class Combo {
    private String id;
    private Long lastModificationDate;
    private String name;
    private Integer hits;
    private Integer hitsLimit;
    private Integer level;
//    @JsonInclude(JsonInclude.Include.NON_NULL) private Integer dailyCount;
//    @JsonInclude(JsonInclude.Include.NON_NULL) private Integer weeklyCount;

    public Combo() {
        setLevel(0);
        setHits(0);
        setLastModificationDate(new Date().getTime());
        setId(new IdGenerator().getNextId()); // TODO: inject id generator
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public Long getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Long lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getHitsLimit() {
        return hitsLimit;
    }

    public void setHitsLimit(Integer hitsLimit) {
        this.hitsLimit = hitsLimit;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
