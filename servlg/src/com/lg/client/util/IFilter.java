package com.lg.client.util;

public interface IFilter<T> {
    boolean isValid(T value,String filter);
}

