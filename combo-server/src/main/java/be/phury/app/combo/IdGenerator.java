package be.phury.app.combo;

import java.util.UUID;

/**
 * Created by Phury
 */
public class IdGenerator {
    public String getNextId() {
        return UUID.randomUUID().toString();
    }
}
