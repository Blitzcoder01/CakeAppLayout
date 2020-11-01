package com.hello.cakeapp.RealmDatabase;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CakeData extends RealmObject {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @PrimaryKey
    private long id ;
    private String image="";
    private String title="" ;
    private String weight="";
    private String type="";
    private String detail="";
    private String cost="";


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String flavour) {
        this.detail = flavour;
    }


}




