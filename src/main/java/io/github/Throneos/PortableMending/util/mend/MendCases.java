package io.github.Throneos.PortableMending.util.mend;

import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;


public class MendCases {
    public static boolean mendHand(Player player, Boolean playerOutput) {
        EntityEquipment equipment = player.getEquipment();
        boolean val = true;
        if (equipment != null) {
            ItemStack item = equipment.getItemInMainHand();
            val = MendHelper.mendItem(item, player, playerOutput);
            equipment.setItemInMainHand(item);
        }
        return val;
    }

    public static boolean mendHand(Player player) {
        return mendHand(player, false);
    }

    public static boolean mendOffHand(Player player, Boolean playerOutput) {
        EntityEquipment equipment = player.getEquipment();
        boolean val = true;
        if (equipment != null) {
            ItemStack item = equipment.getItemInOffHand();
            val = MendHelper.mendItem(item, player, playerOutput);
            equipment.setItemInOffHand(item);
        }
        return val;
    }

    public static boolean mendOffHand(Player player) {
        return mendOffHand(player, false);
    }

    public static boolean mendArmor(Player player) {
        EntityEquipment equipment = player.getEquipment();
        boolean xpLeft = true;
        if (equipment != null) {
            ItemStack[] armor = equipment.getArmorContents();

            for (int i = armor.length - 1; i >= 0; i--) {
                if(armor[i] != null)
                    xpLeft = MendHelper.mendItem(armor[i], player, false);
                if(!xpLeft)
                    break;
            }
            equipment.setArmorContents(armor);
        }
        return xpLeft;
    }

    private static boolean mendInventoryArea(Player player,int start, int stop) {
        PlayerInventory inventory = player.getInventory();
        boolean xpLeft = true;
        for (int i = start; i <= stop; i++) {
            ItemStack item = inventory.getItem(i);
            if(item == null)
                continue;
            xpLeft = MendHelper.mendItem(item, player, false);
            inventory.setItem(i, item);

            if (!xpLeft)
                break;
        }
        return xpLeft;
    }

    public static boolean mendInventory(Player player) {
        return mendInventoryArea(player, 9, 35);
    }

    public static boolean mendHotbar(Player player) {
        return mendInventoryArea(player, 0, 8);
    }

}
