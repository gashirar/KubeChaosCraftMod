package io.github.gashirar.kubechaoscraft;

import io.github.gashirar.kubechaoscraft.config.KubeChaosCraftConfig;
import io.github.gashirar.kubechaoscraft.kubernetes.ExecutorSupplier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Mod("kubechaoscraftmod")
@Mod.EventBusSubscriber(modid = KubeChaosCraftMod.MOD_ID, bus = Bus.MOD)
public class KubeChaosCraftMod {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "kubechaoscraftmod";
    public static io.github.gashirar.kubechaoscraft.KubeChaosCraftMod instance;

    private static ExecutorService executor;
    public static ExecutorSupplier supplier;

    public KubeChaosCraftMod() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();

        modLoadingContext.registerConfig(ModConfig.Type.COMMON, KubeChaosCraftConfig.COMMON_SPEC);

        executor = Executors.newSingleThreadExecutor();
        supplier = new ExecutorSupplier(executor);

        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onServerStopping(FMLServerStoppingEvent event) {
        KubeChaosCraftMod.LOGGER.info("FMLServerStoppingEvent fire.");
        executor.shutdownNow();
    }
}
