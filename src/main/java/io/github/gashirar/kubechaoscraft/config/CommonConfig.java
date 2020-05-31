package io.github.gashirar.kubechaoscraft.config;

import io.github.gashirar.kubechaoscraft.KubeChaosCraftMod;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class CommonConfig {

    final ForgeConfigSpec.DoubleValue threshold;
    final ForgeConfigSpec.ConfigValue<List<String>> targetNamespaces;

    CommonConfig(final ForgeConfigSpec.Builder builder) {
        builder.push("general");
        targetNamespaces = builder
                .comment("Target Namespaces.")
                .translation(KubeChaosCraftMod.MOD_ID + ".config.targetNamespaces")
                .define("targetNamespaces", new ArrayList<>());
        threshold = builder
                .comment("Delete Pod Probability.")
                .translation(KubeChaosCraftMod.MOD_ID + ".config.threshold")
                .defineInRange("threshold", 0, 0, 1.0);
        builder.pop();
    }

}