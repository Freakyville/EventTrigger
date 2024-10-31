package io.github.freakyville.eventtrigger.models;

import java.util.ArrayList;
import java.util.List;

public class EntityEventModel {
    private final String name;
    private final boolean enabled;
    private final List<String> entities;
    private final List<String> commands;

    public EntityEventModel(String name, boolean enabled, List<String> entities, List<String> commands) {
        this.name = name;
        this.enabled = enabled;
        this.entities = entities;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<String> getEntities() {
        return new ArrayList<>(entities);
    }

    public List<String> getCommands() {
        return new ArrayList<>(commands);
    }
}
