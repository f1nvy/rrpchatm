package ff1nvy.dev.rrpchatm.commands;

import ff1nvy.dev.rrpchatm.Core;
import ff1nvy.dev.rrpchatm.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class GlobalDiceCommand extends Command {

    private final ConfigurationSection config = Core.getInstance().getConfig().getConfigurationSection("commands.gdice");
    private final Integer max_value = config.getInt("max_value");
    private final Integer min_value = config.getInt("min_value");

    public GlobalDiceCommand() {
        super("gdice");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) return true;

        String  result = "" + ThreadLocalRandom.current().nextInt(min_value, max_value + 1);

        String message = config.getString("format")
                .replace("%sender%", sender.getName())
                .replace("%result%", result);

        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(message));
        return false;
    }

}
