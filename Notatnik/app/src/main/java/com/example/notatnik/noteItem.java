package com.example.notatnik;

public class noteItem {

    private int mImageResource;
    private String mTextTopic;
    private String mTextContent;
    public noteItem(int imageResource, String Text1, String Text2){
        mImageResource=imageResource;
        mTextTopic=Text1;
        mTextContent=Text2;
    }
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
}
