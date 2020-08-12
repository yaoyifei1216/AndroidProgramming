package com.example.criminalintent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Crime {
    private boolean mSolved;//陋习是否被杜绝
    private Date mDate;//陋习发生的日期
    private String mDateStr;//格式化日期后的字符串
    private String mTitle;//陋习标题
    private UUID mId;//UUID 是Android框架里的Java工具类,在构造方法里调用UUID.randomUUID()产生一个随机唯一ID值
    private boolean mRequiresPolice;//是否要报警
    private String mSuspect;//嫌疑人
    private String mPhone;//嫌疑人电话号码

    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
        dateToStr(mDate);
    }

    private void dateToStr(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        mDateStr = dateFormat.format(date);
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

    public boolean isRequiresPolice() {
        return mRequiresPolice;
    }

    public void setRequiresPolice(boolean requiresPolice) {
        mRequiresPolice = requiresPolice;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getDateStr() {
        return mDateStr;
    }

    public String getDateStr(Date date) {
        dateToStr(date);
        return mDateStr;
    }

    public void setDateStr(String dateStr) {
        mDateStr = dateStr;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }
}
