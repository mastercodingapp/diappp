package mastercoding.diapp.questionslist;

public interface ObservableViewMvc<ListenerType> extends ViewMvc {
    void registerListener(ListenerType listenerType);
    void unregisterListener(ListenerType listenerType);

}
