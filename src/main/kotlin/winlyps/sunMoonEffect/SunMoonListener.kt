package winlyps.sunMoonEffect

import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.RayTraceResult

class SunMoonListener(private val plugin: SunMoonEffect) : Listener {

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player: Player = event.player
        val world: World = player.world
        val time: Long = world.time

        // Check if the player is pointing upwards
        val location = player.location
        val pitch = location.pitch

        if (pitch < -45) { // Player is looking upwards
            // Perform a ray trace from the player's eyes to the sky
            val eyeLocation = player.eyeLocation
            val direction = eyeLocation.direction
            val rayTraceResult: RayTraceResult? = world.rayTraceBlocks(eyeLocation, direction, 256.0)

            if (rayTraceResult == null) { // No blocks in the way
                if (time in 0..12000) { // Daytime
                    player.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 100, 0))
                } else if (time in 12001..24000) { // Nighttime
                    player.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 100, 0))
                }
            }
        }
    }
}