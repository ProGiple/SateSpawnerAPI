package org.satellite.dev.progiple.satespawnerapi.api;

import lombok.Getter;
import org.novasparkle.lunaspring.LunaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class SpawnerAPI {
    private final Map<String, ISAPIComponent> apiMap = new HashMap<>();

    public void register(ISAPIComponent isapiComponent) {
        if (this.isRegistered(isapiComponent.getId()) || this.apiMap
                .values()
                .stream()
                .anyMatch(a -> a.getLunaPlugin().equals(isapiComponent.getLunaPlugin())))
            throw new APIComponentWasRegistered(isapiComponent);
        this.apiMap.put(isapiComponent.getId().toUpperCase(), isapiComponent);
    }

    public void unRegister(ISAPIComponent isapiComponent) {
        this.unRegister(isapiComponent.getId().toUpperCase());
    }

    public void unRegister(String id) {
        this.apiMap.remove(id.toUpperCase());
    }

    public void unRegister(LunaPlugin plugin) {
        this.apiMap.values().stream().filter(i -> i.getLunaPlugin().equals(plugin)).findFirst().ifPresent(this::unRegister);
    }

    public boolean isRegistered(String id) {
        return this.apiMap.containsKey(id.toUpperCase()) && this.apiMap.get(id.toUpperCase()) != null;
    }

    public ISAPIComponent getApi(String id) {
        return this.isRegistered(id.toUpperCase()) ? this.apiMap.get(id.toUpperCase()) : null;
    }

    public Collection<ISAPIComponent> getValues() {
        return this.apiMap.values()
                .stream()
                .filter(ISAPIComponent::isLoaded)
                .collect(Collectors.toSet());
    }

    public final static class SpawnerAPIIsNullException extends RuntimeException {
        public SpawnerAPIIsNullException(String id) {
            super(String.format("Не удалось выполнить действие API, потому что он не зарегистрирован: %s", id.toUpperCase()));
        }
    }

    public final static class APIComponentWasRegistered extends RuntimeException {
        public APIComponentWasRegistered(ISAPIComponent isapiComponent) {
            super(String.format("API %s плагина %s уже было зарегистрировано!",
                    isapiComponent.getId().toUpperCase(), isapiComponent.getLunaPlugin().getName()));
        }
    }
}
