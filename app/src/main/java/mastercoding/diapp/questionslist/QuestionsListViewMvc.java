package mastercoding.diapp.questionslist;

import java.util.List;
import java.util.Observable;

import mastercoding.diapp.Question;

public interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {
    interface Listener {
        void onQuestionClicked(Question question);
    }
    void bindQuestions(List<Question> questions);
}
