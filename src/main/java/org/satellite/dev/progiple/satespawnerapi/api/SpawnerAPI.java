package org.satellite.dev.progiple.satespawnerapi.api;

import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class SpawnerAPI {
    private final Map<String, ISAPIComponent> apiMap = new HashMap<>();

    public void register(ISAPIComponent isapiComponent) {
        if (this.isRegistered(isapiComponent.getId().toUpperCase())) throw new APIComponentWasRegistered(isapiComponent);
        isapiComponent.loadComponent();
        this.apiMap.put(isapiComponent.getId().toUpperCase(), isapiComponent);
    }

    public void unRegister(ISAPIComponent component) {
        component.unloadComponent();
        this.apiMap.remove(component.getId().toUpperCase());
    }

    public void unRegister(String id) {
        ISAPIComponent component = this.getApi(id);
        if (component != null) this.unRegister(component);
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
            super(String.format("API %s уже было зарегистрировано!", isapiComponent.getId().toUpperCase()));
        }
    }
}
