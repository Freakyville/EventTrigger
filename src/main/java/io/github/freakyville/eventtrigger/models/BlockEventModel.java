package io.github.freakyville.eventtrigger.models;

import java.util.ArrayList;
import java.util.List;

public class BlockEventModel {
    private final String name;
    private final boolean enabled;
    private final List<String> blocks;

    public BlockEventModel(String name, boolean enabled, List<String> blocks) {
        this.name = name;
        this.enabled = enabled;
        this.blocks = blocks;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<String> getBlocks() {
        return new ArrayList<>(blocks);
    }
}
