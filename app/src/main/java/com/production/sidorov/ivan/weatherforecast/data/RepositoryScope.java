package com.production.sidorov.ivan.weatherforecast.data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Иван on 18.08.2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface RepositoryScope {
}
