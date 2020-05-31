package io.github.gashirar.kubechaoscraft.config;

import io.github.gashirar.kubechaoscraft.KubeChaosCraftMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

@Mod.EventBusSubscriber(modid = KubeChaosCraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KubeChaosCraftConfig {
    public static final ForgeConfigSpec COMMON_SPEC;
    static final CommonConfig COMMON;

    static {
        {
            final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
            COMMON = specPair.getLeft();
            COMMON_SPEC = specPair.getRight();
        }
    }

    // Common
    public static List<String> targetNamespaces;
    public static double threshold;

    public static void bakeCommon(final ModConfig config) {
        KubeChaosCraftConfig.targetNamespaces = KubeChaosCraftConfig.COMMON.targetNamespaces.get();
        KubeChaosCraftConfig.threshold = KubeChaosCraftConfig.COMMON.threshold.get();
    }
}
