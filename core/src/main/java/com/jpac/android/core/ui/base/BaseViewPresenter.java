package com.jpac.android.core.ui.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by jpcarabuena on 23/4/2017.
 *
 */
public class BaseViewPresenter<V extends BasePresenterView> {

    private WeakReference<V> baseView;
    private V nullView;

    protected BaseViewPresenter() {
        nullView = NullView.of(internalGetViewInterfaceClass());
    }

    public void create() {}

    public void attachView(@NonNull V view) {
        baseView = new WeakReference<V>(view);
    }

    public void resumeView() {}

    public void pauseView() {}

    public void detachView(){
        baseView = null;
    }

    public void destroy() {}

    /**
     * Get the argument of parameterized super class
     * @return
     */
    @SuppressWarnings("unchecked") private Class<V> internalGetViewInterfaceClass() {
        Class clazz = getClass();
        Type genericSuperclass;
        while (true) {
            genericSuperclass = clazz.getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                break;
            }
            clazz = clazz.getSuperclass();
        }
        return (Class<V>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    }


    /**
     * Gets the currently attached view. The view is attached between the lifecycle callbacks
     *
     * @return the currently attached view of this presenter, {@code null} when no view is attached.
     */

    @Nullable
    public V getView() {
        if(baseView!=null && baseView.get()!=null) return baseView.get();
        return nullView;
    }
}
