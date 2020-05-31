package io.github.gashirar.kubechaoscraft.event;

import io.github.gashirar.kubechaoscraft.KubeChaosCraftMod;
import io.github.gashirar.kubechaoscraft.config.KubeChaosCraftConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KubeChaosCraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LivingKilledByPlayerEvent {
    @SubscribeEvent
    public static void LivingKilledByPlayerEvent(LivingDeathEvent event) {
        Entity killer = event.getSource().getTrueSource();
        if (killer != null && killer instanceof PlayerEntity) {
            if (new java.util.Random().nextDouble() < KubeChaosCraftConfig.threshold) {
                KubeChaosCraftMod.supplier.failureInjection();
            }
        }
    }
}