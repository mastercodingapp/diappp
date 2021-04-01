package mastercoding.diapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;

import mastercoding.diapp.detailsQuestion.QuestionDetialsActivity;
import mastercoding.diapp.questionslist.QuestionsListViewMVCImpl;
import mastercoding.diapp.questionslist.QuestionsListViewMvc;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionsListActivity extends AppCompatActivity implements
        Callback<QuestionsListResponseSchema>, QuestionsListViewMvc.Listener

{

    private StackoverflowApi mStackoverflowApi;
    private Call<QuestionsListResponseSchema> mCall;

    private QuestionsListViewMvc mViewMVC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewMVC = new QuestionsListViewMVCImpl(LayoutInflater.from(this), null);
        setContentView(mViewMVC.getRootView());



        // Initiate Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mStackoverflowApi = retrofit.create(StackoverflowApi.class);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMVC.registerListener(this);
        mCall = mStackoverflowApi.lastActiveQuestions(20);
        mCall.enqueue(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMVC.unregisterListener(this);
        if(mCall !=null){
            mCall.cancel();
        }
    }

    @Override
    public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
        QuestionsListResponseSchema responseSchema;
        if(response.isSuccessful() && (responseSchema = response.body()) != null){
            mViewMVC.bindQuestions(responseSchema.getQuestions());
        }else{
            onFailure(call, null);
        }
    }

    @Override
    public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(), null)
                .commitAllowingStateLoss();
    }


    @Override
    public void onQuestionClicked(Question question) {
        QuestionDetialsActivity.start(QuestionsListActivity.this, question.getId());

    }
}