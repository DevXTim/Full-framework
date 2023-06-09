package api.services;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    // It will create one instance and save all scenario data to the map that it has
    private static ScenarioContext instance;
    private Map<String, Object> context;

    public ScenarioContext() {
        context = new HashMap<String, Object>();
    }

    public static ScenarioContext getInstance() {
        if (instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }

    public void setContext(String key, Object value) {
        context.put(key, value);
    }

    public Object getContext(String key) {
        return context.get(key);
    }
}
