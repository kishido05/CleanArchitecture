package com.jpac.android.core.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.annotation.Annotation;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jpcarabuena on 23/4/2017.
 *
 */
public abstract class BaseViewActivity<T extends BaseViewPresenter, V extends BasePresenterView> extends AppCompatActivity {

    protected T baseViewPresenter;
    protected V baseView;

    private Unbinder unbinder;

    public abstract T initializeCore();
    public abstract V initializeView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Annotation annotation = getClass().getAnnotation(Screen.class);
        if (annotation != null) {
            int layout = ((Screen) annotation).resourceLayout();
            setContentView(layout);
        }

        unbinder = ButterKnife.bind(this);

        baseViewPresenter = initializeCore();
        baseView = initializeView();

        baseViewPresenter.create();
    }

    @Override
    protected void onStart() {
        super.onStart();

        baseViewPresenter.attachView(baseView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        baseViewPresenter.resumeView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        baseViewPresenter.pauseView();
    }

    @Override
    protected void onStop() {
        super.onStop();

        baseViewPresenter.detachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
        baseViewPresenter.destroy();
    }
}
