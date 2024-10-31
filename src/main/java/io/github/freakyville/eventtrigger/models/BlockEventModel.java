package io.github.freakyville.eventtrigger.models;

import java.util.ArrayList;
import java.util.List;

public class BlockEventModel {
    private final String name;
    private final boolean enabled;
    private final List<String> blocks;
    private final List<String> commands;

    public BlockEventModel(String name, boolean enabled, List<String> blocks, List<String> commands) {
        this.name = name;
        this.enabled = enabled;
        this.blocks = blocks;
        this.commands = commands;
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

    public List<String> getCommands() {
        return new ArrayList<>(commands);
    }
}
