package ff1nvy.dev.rrpchatm.commands;

import ff1nvy.dev.rrpchatm.Core;
import ff1nvy.dev.rrpchatm.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class GlobalCoinCommand extends Command {

    private final ConfigurationSection config = Core.getInstance().getConfig().getConfigurationSection("commands.gcoin");
    private final String obverse = config.getString("obverse");
    private final String reverse = config.getString("reverse");

    public GlobalCoinCommand() {
        super("gcoin");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) return true;

        String result = Utils.getRandom(2) == 0 ? obverse : reverse;

        String message = config.getString("format")
                .replace("%sender%", sender.getName())
                .replace("%result%", result);

        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(message));
        return false;
    }

}
