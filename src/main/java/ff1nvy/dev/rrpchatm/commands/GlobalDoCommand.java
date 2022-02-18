package ff1nvy.dev.rrpchatm.commands;

import ff1nvy.dev.rrpchatm.Core;
import ff1nvy.dev.rrpchatm.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class GlobalDoCommand extends Command {

    private final ConfigurationSection config = Core.getInstance().getConfig().getConfigurationSection("commands.gdo");

    public GlobalDoCommand() {
        super("gdo");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) return true;

        if (args.length < 1) {
            sender.sendMessage(config.getString("usage"));
            return true;
        }

        String message = config.getString("format")
                .replace("%sender%", sender.getName())
                .replace("%action%", Utils.extractMessage(args));


        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(message));
        return false;
    }

}