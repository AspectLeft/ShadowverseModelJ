package model.event;

import java.util.HashMap;
import java.util.Map;

public abstract class EventBase {
    private final Map<String, Object> extraMap = new HashMap<>();

    protected EventBase() {
    }

    public void putExtra(final String key, final Object value) {
        extraMap.put(key, value);
    }

    public Object getExtra(final String key) {
        return extraMap.get(key);
    }
}
