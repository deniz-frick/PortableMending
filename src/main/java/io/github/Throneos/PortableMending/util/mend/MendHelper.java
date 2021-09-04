package io.github.Throneos.PortableMending.util.mend;

import io.github.Throneos.PortableMending.util.ConfigHelper;
import io.github.Throneos.PortableMending.util.FeedbackHelper;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;


public class MendHelper {
    private MendHelper() {
    }


    protected static boolean mendItem(ItemStack item, Player player, Boolean playerOutput) {
        if (playerOutput ? isMendable(item, player) : isMendable(item)) {
            int experienceBudget = player.getTotalExperience();

            int cost = mendingCost(item);

            if (cost <= 0) {
                if (playerOutput)
                    player.sendMessage("This item isn't damaged");
                return true;
            }

            if (player.getTotalExperience() >= cost) {
                player.giveExp(-cost);
                repairItem(item);
                if (playerOutput)
                    FeedbackHelper.feedbackSuccess(player);
            } else {
                int gainedDurability = repairableAmount(experienceBudget);

                if (gainedDurability > 0) {
                    player.giveExp(-experienceBudget);
                    repairItem(item, gainedDurability);

                    if (playerOutput) {
                        FeedbackHelper.feedbackPartialFail(player);
                    }
                } else {
                    if (playerOutput)
                        FeedbackHelper.feedbackFail(player);
                    return false;
                }

            }
        }

        return true;
    }

    protected static boolean mendItem(ItemStack item, Player player) {
        if (isMendable(item, player)) {
            return mendItem(item, player, false);
        }

        return true;
    }

    private static boolean isMendable(ItemStack item, Player player) {
        if (!item.hasItemMeta() || !(item.getItemMeta() instanceof Damageable)) {
            if (player != null)
                player.sendMessage("This can't be mended.");
            return false;
        }
        if (!item.containsEnchantment(Enchantment.MENDING)) {
            if (player != null)
                player.sendMessage("You can only mend items with the \"Mending\" enchant");
            return false;
        }
        return true;
    }

    private static boolean isMendable(ItemStack item) {
        return isMendable(item, null);
    }

    private static int mendingCost(ItemStack item) {
        if (item.getItemMeta() instanceof Damageable) {
            Damageable itemMeta = (Damageable) item.getItemMeta();
            return mendingCost(itemMeta.getDamage());
        }
        return 0;
    }

    private static int mendingCost(int repairedDurability) {
        return (int) Math.ceil(repairedDurability / ConfigHelper.getInstance().getDurabilityPerXp());
    }

    private static int repairableAmount(int experienceBudget) {
        return (int) (ConfigHelper.getInstance().getDurabilityPerXp() * experienceBudget);

    }

    private static void repairItem(ItemStack item) {
        if (item.getItemMeta() instanceof Damageable) {
            Damageable itemMeta = (Damageable) item.getItemMeta();
            itemMeta.setDamage(0);
            item.setItemMeta((ItemMeta) itemMeta);
        }
    }

    private static void repairItem(ItemStack item, int repairedDurability) {
        if (item.getItemMeta() instanceof Damageable) {
            Damageable itemMeta = (Damageable) item.getItemMeta();
            itemMeta.setDamage(itemMeta.getDamage() - repairedDurability);
            item.setItemMeta((ItemMeta) itemMeta);
        }
    }
}
