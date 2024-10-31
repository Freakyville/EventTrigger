package io.github.freakyville.eventtrigger.commands;

import io.github.freakyville.utilsupdated.commands.ReloadCommand;
import io.github.freakyville.utilsupdated.commands.RootCommand;

public class MainCommand extends RootCommand {

    public MainCommand(ReloadCommand reloadCommand) {
        super("eventtrigger");
        addCommand(reloadCommand);
    }

}
