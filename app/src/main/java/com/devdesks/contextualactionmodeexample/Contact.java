package com.devdesks.contextualactionmodeexample;

/**
 * Created by Devdesk on 8/24/2016.
 */
public class Contact {

    int img_id;
    String name;
    String email;
    public static final String LOG = "Example";

    public Contact(int imgid, String name)
    {
        this.setImg_id(imgid);
        this.setName(name);

    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
