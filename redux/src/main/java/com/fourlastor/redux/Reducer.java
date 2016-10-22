package com.fourlastor.redux;

public interface Reducer<State> {

    State reduce();

    <Action> State reduce(State state, Action action);
}
