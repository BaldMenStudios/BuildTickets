package net.zffu.buildtickets.utils;

import java.io.File;

public class Bundle<T, E> {

    private T t;
    private E e;

    public Bundle(T t, E e) {
        this.t = t;
        this.e = e;
    }

    public T getFirst() {
        return this.t;
    }

    public E getSecond() {
        return this.e;
    }

}
