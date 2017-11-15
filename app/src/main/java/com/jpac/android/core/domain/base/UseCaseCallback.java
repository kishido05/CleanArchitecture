package com.jpac.android.core.domain.base;

/**
 * Created by jpcarabuena on 3/7/2017.
 *
 */
public interface UseCaseCallback<Response, Throwable> {

    void onStart();

    void onComplete(Response response);

    void onError(Throwable throwable);
}
