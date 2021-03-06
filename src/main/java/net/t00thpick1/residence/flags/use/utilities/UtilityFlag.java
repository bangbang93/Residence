package net.t00thpick1.residence.flags.use.utilities;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.t00thpick1.residence.api.PermissionsArea;
import net.t00thpick1.residence.api.ResidenceAPI;
import net.t00thpick1.residence.flags.use.UseFlag;
import net.t00thpick1.residence.locale.LocaleLoader;
import net.t00thpick1.residence.protection.FlagManager;

public abstract class UtilityFlag extends UseFlag {
    public static final String FLAG = LocaleLoader.getString("Flags.Flags.Utility");
    public boolean allowAction(Player player, PermissionsArea area) {
        return area.allowAction(player, FLAG, super.allowAction(player, area));
    }

    protected abstract boolean shouldCheck(Material material);

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onClick(PlayerInteractEvent event) {
        if (isTool(event)) {
            return;
        }
        Player player = event.getPlayer();
        if (isAdminMode(player)) {
            return;
        }
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }
        if (!shouldCheck(block.getType())) {
            return;
        }
        if (!allowAction(player, ResidenceAPI.getPermissionsAreaByLocation(block.getLocation()))) {
            event.setCancelled(true);
            player.sendMessage(LocaleLoader.getString("FlagDeny", LocaleLoader.getString("UseFlagDeny", FLAG)));
        }
    }

    public static void initialize() {
        FlagManager.addFlag(FLAG);
        WorkBenchFlag.initialize();
        EnchantmentTableFlag.initialize();
        BeaconFlag.initialize();
        AnvilFlag.initialize();
        EnderChestFlag.initialize();
        BedFlag.initialize();
    }
}
