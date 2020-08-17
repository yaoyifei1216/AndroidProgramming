package com.example.photogallery;

import androidx.annotation.NonNull;

public class GalleryItem {

    private String imgurl;

    public GalleryItem(String imgurl, String url) {
        imgurl = imgurl;
    }

    @NonNull
    @Override
    public String toString() {
        return "GalleryItem{" +
                "fullstartdate='" + imgurl + '\'' + '}';
    }
}
