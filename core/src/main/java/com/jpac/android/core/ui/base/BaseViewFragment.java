package com.jpac.android.core.ui.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.annotation.Annotation;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jpcarabuena on 23/4/2017.
 *
 */
public abstract class BaseViewFragment<T extends BaseViewPresenter, V extends BasePresenterView> extends Fragment {

    protected T baseViewPresenter;
    protected V baseView;

    private Unbinder unbinder;

    public abstract T initializeCore();
    public abstract V initializeView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View parent = super.onCreateView(inflater, container, savedInstanceState);

        Annotation annotation = getClass().getAnnotation(Screen.class);
        if (annotation != null) {
            int layout = ((Screen) annotation).resourceLayout();
            parent = inflater.inflate(layout, null);
        }

        if (parent != null)
            unbinder = ButterKnife.bind(this, parent);

        baseViewPresenter = initializeCore();
        baseView = initializeView();

        baseViewPresenter.create();

        return parent;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
        baseViewPresenter.destroy();
    }

    @Override
    public void onStart() {
        super.onStart();

        baseViewPresenter.attachView(baseView);
    }

    @Override
    public void onResume() {
        super.onResume();

        baseViewPresenter.resumeView();
    }

    @Override
    public void onPause() {
        super.onPause();

        baseViewPresenter.pauseView();
    }

    @Override
    public void onStop() {
        super.onStop();

        baseViewPresenter.detachView();
    }

}
