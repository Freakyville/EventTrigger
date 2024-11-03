package io.github.freakyville.eventtrigger.models;

public class ExpEventModel {
    private final String name;
    private final boolean enabled;

    public ExpEventModel(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
