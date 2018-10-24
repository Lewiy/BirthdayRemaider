package com.romanenko.lew.birthdayremaider.View.Adapters;

public class ItemGridView {

    private String content;
    private String imageResourse;

    public ItemGridView(String content, String imageResourse) {
        this.content = content;
        this.imageResourse = imageResourse;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageResourse() {
        return imageResourse;
    }

    public void setImageResourse(String imageResourse) {
        this.imageResourse = imageResourse;
    }


}
