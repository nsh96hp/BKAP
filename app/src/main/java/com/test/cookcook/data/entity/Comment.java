package com.test.cookcook.data.entity;

/**
 * Created by NSH on 10/01/2018.
 */

public class Comment {
    //Cai bang nay t nghi de tren firebase
    private String idComment;
    private String idUser;
    private String idCooked;
    private String content;
    private String image;

    public Comment() {
    }

    public Comment(String idComment, String idUser, String idCooked, String content, String image) {
        this.idComment = idComment;
        this.idUser = idUser;
        this.idCooked = idCooked;
        this.content = content;
        this.image = image;
    }

    public Comment(String idUser, String idCooked, String content, String image) {
        this.idUser = idUser;
        this.idCooked = idCooked;
        this.content = content;
        this.image = image;
    }

    public String getIdComment() {
        return idComment;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdCooked() {
        return idCooked;
    }

    public void setIdCooked(String idCooked) {
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
