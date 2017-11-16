package com.jpac.android.core.ui.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jpcarabuena on 23/4/2017.
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Screen {

    /**
     * The name that will be used for posting State
     */
    String name() default "";

    /**
     * The id for the layout for this screen
     */
    int resourceLayout();
}

