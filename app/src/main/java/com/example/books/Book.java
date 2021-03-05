package com.example.books;

public class Book {
    private String mImageUrl;
    private String mBookName;
    private int mPages;
    private String mPublisher;
    private String mSelfUrl;
    private String mAuthorName;

    public Book(){}
    public Book(String mImageUrl, String mBookName, int mPages, String mPublisher, String mSelfUrl, String mAuthorName) {
        this.mImageUrl = mImageUrl;
        this.mBookName = mBookName;
        this.mPages = mPages;
        this.mPublisher = mPublisher;
        this.mSelfUrl = mSelfUrl;
        this.mAuthorName = mAuthorName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmBookName() {
        return mBookName;
    }

    public int getmPages() {
        return mPages;
    }

    public String getmAuthorName() {
        return mAuthorName;
    }

    public String getmPublisher() {
        return mPublisher;
    }

    public String getmSelfUrl() {
        return mSelfUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public void setmBookName(String mBookName) {
        this.mBookName = mBookName;
    }

    public void setmPages(int mPages) {
        this.mPages = mPages;
    }

    public void setmPublisher(String mPublisher) {
        this.mPublisher = mPublisher;
    }

    public void setmSelfUrl(String mSelfUrl) {
        this.mSelfUrl = mSelfUrl;
    }

    public void setmAuthorName(String mAuthorName) {
        this.mAuthorName = mAuthorName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "mImageUrl='" + mImageUrl + '\'' +
                ", mBookName='" + mBookName + '\'' +
                ", mPages=" + mPages +
                ", mPublisher='" + mPublisher + '\'' +
                ", mSelfUrl='" + mSelfUrl + '\'' +
                ", mAuthorName='" + mAuthorName + '\'' +
                '}';
    }
}
