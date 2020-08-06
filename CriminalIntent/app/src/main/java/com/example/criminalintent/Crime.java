package com.example.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private boolean mSolved;//陋习是否被杜绝
    private Date mDate;//陋习发生的日期
    private String mTitle;//陋习标题
    private UUID mId;//UUID 是Android框架里的Java工具类,在构造方法里调用UUID.randomUUID()产生一个随机唯一ID值

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }
}
