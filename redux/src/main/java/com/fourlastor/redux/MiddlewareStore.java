package com.fourlastor.redux;

import java.util.List;

class MiddlewareStore<State> implements Store<State> {

    private final Store<State> store;
    private final Dispatcher chain;

    MiddlewareStore(Store<State> store, List<Middleware> middlewares) {
        this.store = store;
        this.chain = createChain(this, store, middlewares);
    }

    private static <State> Dispatcher createChain(final MiddlewareApi<State> api,
                                                  final Store<State> store,
                                                  List<Middleware> middlewares) {
        Dispatcher next = store;

        for (int i = middlewares.size() - 1; i >= 0; i--) {
            final Middleware middleware = middlewares.get(i);
            final Dispatcher previousNext = next;
            next = new Dispatcher() {
                @Override
                public <OtherAction> void dispatch(OtherAction action) {
                    middleware.dispatch(api, previousNext, action);
                }
            };
        }
        return next;
    }

    @Override
    public State getState() {
        return store.getState();
    }

    @Override
    public <Action> void dispatch(Action initialAction) {
        chain.dispatch(initialAction);
    }

    @Override
    public void addListener(Listener listener) {
        store.addListener(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        store.removeListener(listener);
    }
}
