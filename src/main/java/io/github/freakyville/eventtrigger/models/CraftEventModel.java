package io.github.freakyville.eventtrigger.models;

import java.util.ArrayList;
import java.util.List;

public class CraftEventModel {
    private final String name;
    private final boolean enabled;
    private final List<String> items;
    private final List<String> command;

    public CraftEventModel(String name, boolean enabled, List<String> items, List<String> command) {
        this.name = name;
        this.enabled = enabled;
        this.items = items;
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }

    public List<String> getCommands() {
        return new ArrayList<>(command);
    }
}
