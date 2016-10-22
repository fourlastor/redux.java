package com.fourlastor.redux;

public interface Store<State> extends MiddlewareApi<State> {

    void addListener(Listener listener);

    void removeListener(Listener listener);

    interface Listener {

        void onStateChanged();
    }
}
