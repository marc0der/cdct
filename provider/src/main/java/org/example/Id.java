package org.example;

import java.util.UUID;

public class Id {
    private final Integer id;
    private final UUID uuid;

    public Id(Integer id, UUID uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

}
