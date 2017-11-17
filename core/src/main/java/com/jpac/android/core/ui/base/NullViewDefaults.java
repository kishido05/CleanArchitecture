package com.jpac.android.core.ui.base;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

/**
 * Created by jpcarabuena on 28/6/2017.
 *
 */
class NullViewDefaults {
    private static final Map<Class<?>, Object> DEFAULTS = unmodifiableMap(new HashMap<Class<?>, Object>() {
        {
            put(Boolean.TYPE, false);
            put(Byte.TYPE, (byte) 0);
            put(Character.TYPE, '\000');
            put(Double.TYPE, 0.0d);
            put(Float.TYPE, 0.0f);
            put(Integer.TYPE, 0);
            put(Long.TYPE, 0L);
            put(Short.TYPE, (short) 0);
        }
    });

    private NullViewDefaults() {
    }

    @SuppressWarnings("unchecked") public static <T> T defaultValue(Class<T> type) {
        return (T) DEFAULTS.get(type);
    }
}