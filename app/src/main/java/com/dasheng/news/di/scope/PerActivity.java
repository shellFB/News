package com.dasheng.news.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author shell
 * @version 1.0
 * @desc
 * @since 2016/11/17 0017 11:03
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PerActivity {
}
