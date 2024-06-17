package org.example.overchargedhell.models;

public class Profile {
    public int id;
    public String name;
    public String email;
    public String picture;
    public int age;
    public int userid;

    public Profile(int id, String name, String email, String picture, int age, int userid) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.age = age;
        this.userid = userid;
    }
}
