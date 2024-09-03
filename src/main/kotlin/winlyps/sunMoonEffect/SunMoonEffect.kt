package winlyps.sunMoonEffect

import org.bukkit.plugin.java.JavaPlugin

class SunMoonEffect : JavaPlugin() {

    override fun onEnable() {
        // Register the event listener
        server.pluginManager.registerEvents(SunMoonListener(this), this)
        logger.info("SunMoonEffect plugin has been enabled!")
    }

    override fun onDisable() {
        logger.info("SunMoonEffect plugin has been disabled!")
    }
}