package com.cely404.nomad.data;

import android.media.Image;

/**
 * Created by aracely_payan93 on 3/12/16.
 */
public class Person {
    private String name;
    private int age;
    private String sex;
    private String aboutMessage;
    private Image image;

    public Person(String name, int age, String sex, String aboutMessage, Image image){
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.aboutMessage = aboutMessage;
        this.image = image;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getAboutMessage() {
        return aboutMessage;
    }

    public void setAboutMessage(String aboutMessage) {
        this.aboutMessage = aboutMessage;
    }
}
