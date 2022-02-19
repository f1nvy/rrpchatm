package ff1nvy.dev.rrpchatm;

import ff1nvy.dev.rrpchatm.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core instance;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        commandManager = new CommandManager(this);
        commandManager
                .register(new MeCommand())
                .register(new GlobalMeCommand())
                .register(new TryCommand())
                .register(new DoCommand())
                .register(new DiceCommand())
                .register(new GlobalDiceCommand())
                .register(new GlobalDoCommand())
                .register(new GlobalTryCommand())
                .register(new CoinCommand())
                .register(new GlobalCoinCommand())
                .register(new WhisperCommand());
    }

    @Override
    public void onDisable() {
        commandManager.unregisterAll();
    }

    public static Core getInstance() {
        return instance;
    }

}