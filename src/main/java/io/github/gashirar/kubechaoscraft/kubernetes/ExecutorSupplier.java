package io.github.gashirar.kubechaoscraft.kubernetes;

import io.github.gashirar.kubechaoscraft.KubeChaosCraftMod;
import io.github.gashirar.kubechaoscraft.config.KubeChaosCraftConfig;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1DeleteOptions;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public class ExecutorSupplier {
    private final Executor executor;

    public ExecutorSupplier(Executor executor) {
        this.executor = executor;
    }

    private boolean validate(String namespace) {
        return KubeChaosCraftConfig.targetNamespaces.contains(namespace);
    }

    public void failureInjection() {
        executor.execute(() -> {
            try {
                ApiClient client = Config.defaultClient();
                Configuration.setDefaultApiClient(client);

                CoreV1Api api = new CoreV1Api();
                V1PodList list = api.listPodForAllNamespaces(
                        null,
                        null,
                        null,
                        null,
                        null, null,
                        null,
                        null,
                        null);
                List<V1Pod> shuffled = new ArrayList<>(list.getItems());
                Collections.shuffle(shuffled);
                for (V1Pod item : shuffled) {
                    if (validate((item.getMetadata().getNamespace()))) {
                        KubeChaosCraftMod.LOGGER.info("Delete Target -> Namespace : " + item.getMetadata().getNamespace() + ", Pod : " + item.getMetadata().getName());
                        api.deleteNamespacedPod(
                                item.getMetadata().getName(),
                                item.getMetadata().getNamespace(),
                                null,
                                null,
                                null,
                                null,
                                null,
                                new V1DeleteOptions());
                        break;
                    }
                }
            } catch (Exception e) {
                // TODO improve this.
                KubeChaosCraftMod.LOGGER.error(e);
            }
        });
    }
}