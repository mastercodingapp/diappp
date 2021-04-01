package mastercoding.diapp.questionslist;

import android.content.Context;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.StringRes;

public abstract class BaseViewMVC<ListenerType> extends BaseObservable<ListenerType>
        implements ObservableViewMvc<ListenerType>{

    private View mRootView;

    @Override
    public View getRootView() {
        return mRootView;
    }

    protected void setRootView(View rootView){
        mRootView = rootView;
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewById(@IdRes int id){
        return (T) mRootView.findViewById(id);
    }


    protected Context getContext(){
        return getRootView().getContext();
    }

    protected String getString(@StringRes int id){return getContext().getString(id);}
    protected String getString(@StringRes int id, Object... formatArgs){
        return getContext().getString(id, formatArgs);
    }
}
