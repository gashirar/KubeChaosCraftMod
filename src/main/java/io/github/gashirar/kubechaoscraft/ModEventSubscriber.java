package io.github.gashirar.kubechaoscraft;

import io.github.gashirar.kubechaoscraft.config.KubeChaosCraftConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod.EventBusSubscriber(modid = KubeChaosCraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModEventSubscriber {

    private static final Logger LOGGER = LogManager.getLogger(KubeChaosCraftMod.MOD_ID + " Mod Event Subscriber");

    /**
     * This method will be called by Forge when a config changes.
     */
    @SubscribeEvent
    public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
        final ModConfig config = event.getConfig();

        if (config.getSpec() == KubeChaosCraftConfig.COMMON_SPEC) {
            KubeChaosCraftConfig.bakeCommon(config);
        }
    }
}


