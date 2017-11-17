package com.jpac.android.core.domain.base;

/**
 * Created by jpcarabuena on 3/7/2017.
 *
 */
public interface UseCase<Params, Callback extends UseCaseCallback> {

    void execute(Callback callback, Params... params);
}
