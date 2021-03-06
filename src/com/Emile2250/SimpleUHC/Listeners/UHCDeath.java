package com.Emile2250.SimpleUHC.Listeners;

import com.Emile2250.SimpleUHC.SimpleUHC;
import com.Emile2250.SimpleUHC.Stats.StatsHandler;
import com.Emile2250.SimpleUHC.UHC.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class UHCDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (e.getEntity() != null) { // Makes sure it was a player that died
            Player player = e.getEntity(); // Creates a variable for future use

            for (Game game : SimpleUHC.getInstance().getGames()) {
                if (game.getPlayers().contains(player)) { // Checks if the player died within a UHC game

                    if (e.getEntity().getKiller() != null) {
                        StatsHandler.addKill(e.getEntity().getKiller());
                        game.addKill(e.getEntity().getKiller());
                    }

                    game.removePlayer(player); // Removes the player from the game.
                    player.spigot().respawn(); // Force respawns to allow for world deletion if needed
                    return; // Since we handled the death there is no need to continue the code
                }
            }
        }
    }
}
