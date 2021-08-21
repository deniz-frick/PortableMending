package io.github.Throneos.PortableMending.util;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class FeedbackHelper {
    public static void feedbackSuccess(Player player){
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.4f, RandomSingleton.getInstance().nextFloat());
    }

    public static void feedbackFail(Player player){
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1f, .6f);
        player.sendMessage("You need more XP");
    }
    public static void feedbackPartialFail(Player player){
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1f, .6f);
        player.sendMessage("You ran out of XP");
    }

    public static void feedbackFromBool(Player player, boolean isPositive){
        if(isPositive){
            feedbackSuccess(player);
        }else{
            feedbackFail(player);
        }
    }
}
