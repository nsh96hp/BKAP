package com.test.cookcook.data.entity;

/**
 * Created by NSH on 10/01/2018.
 */

public class Comment {
    //Cai bang nay t nghi de tren firebase
    private int idComment;
    private int idUser;
    private int idCooked;
    private String content;
    private String image;

    public Comment() {
    }

    public Comment(int idComment, int idUser, int idCooked, String content, String image) {
        this.idComment = idComment;
        this.idUser = idUser;
        this.idCooked = idCooked;
        this.content = content;
        this.image = image;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getIdCooked() {
        return idCooked;
    }

    public void setIdCooked(int idCooked) {
        this.idCooked = idCooked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
