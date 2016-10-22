package com.fourlastor.redux;

public interface Dispatcher {

    <Action> void dispatch(Action action);
}
