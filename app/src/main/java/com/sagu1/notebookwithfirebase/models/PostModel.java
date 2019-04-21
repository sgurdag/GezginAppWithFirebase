package com.sagu1.notebookwithfirebase.models;

public class PostModel {

    private int postPicture;
    private String postName;
    private String postDescription;
    public boolean fromMe = true;

    public PostModel(int postPicture, String postName, String postDescription) {
        this.postPicture = postPicture;
        this.postName = postName;
        this.postDescription = postDescription;
    }

    public int getPostPicture() {
        return postPicture;
    }

    public void setPostPicture(int postPicture) {
        this.postPicture = postPicture;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }
}
