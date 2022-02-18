package ff1nvy.dev.rrpchatm.commands;

import ff1nvy.dev.rrpchatm.Core;
import ff1nvy.dev.rrpchatm.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class GlobalTryCommand extends Command {

    private final ConfigurationSection config = Core.getInstance().getConfig().getConfigurationSection("commands.gtry");
    private final List<String> results = config.getStringList("results");
    private final Random random = new Random();

    public GlobalTryCommand() {
        super("gtry");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) return true;

        if (args.length < 1) {
            sender.sendMessage(config.getString("usage"));
            return true;
        }

        String result = results.get(Utils.getRandom(results.size()));

        String message = config.getString("format")
                .replace("%sender%", sender.getName())
                .replace("%action%", Utils.extractMessage(args))
                .replace("%result%", result);

        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(message));
        return false;
    }

}