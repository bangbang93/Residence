package net.t00thpick1.residence.flags.use.utilities;

import net.t00thpick1.residence.Residence;
import net.t00thpick1.residence.api.PermissionsArea;
import net.t00thpick1.residence.locale.LocaleLoader;
import net.t00thpick1.residence.protection.FlagManager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class AnvilFlag extends UtilityFlag implements Listener {
    public static final String FLAG = LocaleLoader.getString("Flags.Flags.Anvil");
    public boolean allowAction(Player player, PermissionsArea area) {
        return area.allowAction(player, FLAG, super.allowAction(player, area));
    }

    protected boolean shouldCheck(Material material) {
        return material == Material.ANVIL;
    }

    public static void initialize() {
        FlagManager.addFlag(FLAG);
        Plugin plugin = Residence.getInstance();
        plugin.getServer().getPluginManager().registerEvents(new AnvilFlag(), plugin);
    }
}
