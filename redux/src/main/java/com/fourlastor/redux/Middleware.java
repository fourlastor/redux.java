package com.fourlastor.redux;

public interface Middleware {

    <Action, State> void dispatch(MiddlewareApi<State> api, Dispatcher next, Action action);
}
