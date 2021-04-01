package mastercoding.diapp;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("title")
    private final String mTitle;

    @SerializedName("question_id")
    private final String mId;


    public Question(String mTitle, String mId) {
        this.mTitle = mTitle;
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getId() {
        return mId;
    }
}
