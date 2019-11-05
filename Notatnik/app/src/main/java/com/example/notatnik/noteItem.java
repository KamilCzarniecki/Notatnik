package com.example.notatnik;

import android.os.Parcel;
import android.os.Parcelable;

public class noteItem implements Parcelable {

    private int mImageResource;
    private String mTextTopic;
    private String mTextContent;
    public noteItem(int imageResource, String Text1, String Text2){
        mImageResource=imageResource;
        mTextTopic=Text1;
        mTextContent=Text2;
    }

    protected noteItem(Parcel in) {
        mImageResource = in.readInt();
        mTextTopic = in.readString();
        mTextContent = in.readString();
    }

    public static final Creator<noteItem> CREATOR = new Creator<noteItem>() {
        @Override
        public noteItem createFromParcel(Parcel in) {
            return new noteItem(in);
        }

        @Override
        public noteItem[] newArray(int size) {
            return new noteItem[size];
        }
    };

    public int getImageResource() {
        return mImageResource;
    }

    public String getTextTopic() {
        return mTextTopic;
    }

    public String getTextContent() {
        return mTextContent;
    }

    public void setmTextTopic(String mTextTopic) {
        this.mTextTopic = mTextTopic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mImageResource);
        dest.writeString(mTextTopic);
        dest.writeString(mTextContent);
    }
}
