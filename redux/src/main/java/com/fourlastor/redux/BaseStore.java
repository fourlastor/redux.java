package com.fourlastor.redux;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class BaseStore<State> implements Store<State> {

    private final Reducer<State> reducer;
    private final List<Listener> listeners;

    private State state;

    BaseStore(Reducer<State> reducer) {
        this.state = reducer.reduce();
        this.reducer = reducer;
        this.listeners = new CopyOnWriteArrayList<>();
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public <Action> void dispatch(Action action) {
        state = reducer.reduce(state, action);

        for (Listener listener : listeners) {
            listener.onStateChanged();
        }
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }
}
