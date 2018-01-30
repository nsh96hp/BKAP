package com.test.cookcook.data.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by NSH on 10/01/2018.
 */

public class Cooked implements Serializable{
    private String idCooked; //////////
    private String name;
    private String intro;
    private int people;
    private int like; //Chuyen sang like =))
    private int share;
    private int download;
    private String idUser; ///////////Xem set de String
    private String image;
    /////////////////////
    //private int flag; //voi flag la 0 thi món chưa được đăng tải và chỉ người chủ mới thấy. 1 khi đăng tải món rồi <<< Xem xét ở 2 mới public và người chuyển sang 2 là mình, mình duyệt bài >>>
    //Them trang thai, luot tai


    public Cooked(String name, String intro, int people, int like, int share, int download, String idUser, String image) {
        this.name = name;
        this.intro = intro;
        this.people = people;
        this.like = like;
        this.share = share;
        this.download = download;
        this.idUser = idUser;
        this.image = image;
    }

    public Cooked(String name, String intro, int people, String idUser, String image) {
        this.name = name;
        this.intro = intro;
        this.people = people;
        this.idUser = idUser;
        this.image = image;
    }

    public Cooked(String idCooked, String name, String intro, int people, int like, int share, int download, String idUser, String image) {
        this.idCooked = idCooked;
        this.name = name;
        this.intro = intro;
        this.people = people;
        this.like = like;
        this.share = share;
        this.download = download;
        this.idUser = idUser;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result=new HashMap<>();
        result.put("name",name);
        result.put("intro",intro);
        result.put("people",people);
        result.put("idUser",idUser);
        result.put("image",image);
        return result;
    }

    public Cooked() {
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }


    public String getIdCooked() {
        return idCooked;
    }

    public void setIdCooked(String idCooked) {
        this.idCooked = idCooked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

}
