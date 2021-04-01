package mastercoding.diapp.detailsQuestion;

import mastercoding.diapp.QuestionWithBody;
import mastercoding.diapp.questionslist.ObservableViewMvc;

public interface QuestionDetailsViewMVC extends ObservableViewMvc<QuestionDetailsViewMVC.Listener> {
    interface Listener{

    }
    void bindQuestion(QuestionWithBody question);
}
