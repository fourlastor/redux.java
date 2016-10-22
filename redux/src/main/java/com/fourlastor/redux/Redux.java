package com.fourlastor.redux;

import java.util.List;

public final class Redux {

    private Redux() {
    }

    public static <State> Store<State> createStore(Reducer<State> reducer,
                                                   List<Middleware> middlewares) {
        Store<State> internalStore = new BaseStore<>(reducer);

        return new MiddlewareStore<>(internalStore, middlewares);
    }
}
