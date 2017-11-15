package com.jpac.android.core.ui.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static com.jpac.android.core.ui.base.NullViewDefaults.defaultValue;
import static java.lang.reflect.Proxy.newProxyInstance;


/**
 * Created by jpcarabuena on 28/6/2017.
 *
 */
class NullView {
    private static final InvocationHandler DEFAULT_VALUE = new DefaultValueInvocationHandler();

    private NullView() {
    }

    @SuppressWarnings("unchecked") public static <T> T of(Class<T> interfaceClass) {
        return (T) newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass }, DEFAULT_VALUE);
    }

    private static class DefaultValueInvocationHandler implements InvocationHandler {
        @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return defaultValue(method.getReturnType());
        }
    }
}

