package io.github.freakyville.eventtrigger.models;

import java.util.ArrayList;
import java.util.List;

public class CraftEventModel {
    private final String name;
    private final boolean enabled;
    private final List<String> items;

    public CraftEventModel(String name, boolean enabled, List<String> items) {
        this.name = name;
        this.enabled = enabled;
        this.items = items;
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
}
