package com.cjlib.cleanarchitecture.login.entities;

import com.cjlib.cleanarchitecture.base.Entity;
import com.google.gson.annotations.SerializedName;

public class User implements Entity {

    @SerializedName("user_email")
    public String email;

    @SerializedName("user_password")
    public String password;

    @SerializedName("access_token")
    public String accessToken;

    public String name;

}
