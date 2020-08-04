package com.example.geoquiz;
/*为什么 mTextResId 是 int 类型,而不是 String 类型呢?变量 mTextResId 用来保存地理知识
        问题字符串的资源ID。资源ID总是 int 类型,所以这里设置它为 int 类型。*/
public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}