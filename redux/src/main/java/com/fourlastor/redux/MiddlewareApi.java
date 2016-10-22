package com.fourlastor.redux;

public interface MiddlewareApi<State> extends Dispatcher {

    State getState();
}
