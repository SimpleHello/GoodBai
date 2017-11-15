package com.good.netty.udp.future;

import java.util.concurrent.Future;

/**
 * @author mag
 */
public interface WriteFuture<T> extends Future<T>{


    Throwable cause();

    void setCause(Throwable cause);

    boolean isWriteSuccess();

    void setWriteResult(boolean result);

    String requestId();

    T response();

    void setResponse(String response);

    boolean isTimeout();
}
