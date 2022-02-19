package ff1nvy.dev.rrpchatm.commands;

import ff1nvy.dev.rrpchatm.Core;
import ff1nvy.dev.rrpchatm.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DiceCommand extends Command {

    private final ConfigurationSection config = Core.getInstance().getConfig().getConfigurationSection("commands.dice");
    private final Integer max_value = config.getInt("max_value");
    private final Integer min_value = config.getInt("min_value");

    public DiceCommand() {
        super("dice");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) return true;

        String  result = "" + ThreadLocalRandom.current().nextInt(min_value, max_value + 1);

        String message = config.getString("format")
                .replace("%sender%", sender.getName())
                .replace("%result%", result);

        Utils.getNearbyPlayers((Player) sender, config.getInt("radius"))
                .forEach(p -> p.sendMessage(message));
        return false;
    }

}
