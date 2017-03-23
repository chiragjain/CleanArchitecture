package com.cjlib.cleanarchitecture.components.data;

import java.util.Map;
import java.util.Set;

/**
 * A interface for storing and providing key values
 */
interface KeyValueManager {

    void save(String key, String data);

    void save(String key, boolean data);

    void save(String key, float data);

    void save(String key, int data);

    void save(String key, long data);

    void save(String key, Set<String> data);

    String getString(String key, String defaultValue);

    String getString(String key);

    Integer getInteger(String key, int defaultValue);

    Integer getInteger(String key);

    Float getFloat(String key, float defaultValue);

    Float getFloat(String key);

    Long getLong(String key, long defaultValue);

    Long getLong(String key);

    Boolean getBoolean(String key, boolean defaultValue);

    Boolean getBoolean(String key);

    Map<String, ?> getMap();

    void clear();
}
