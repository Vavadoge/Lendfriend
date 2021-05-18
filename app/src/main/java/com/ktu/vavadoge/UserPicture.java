package com.ktu.vavadoge;

public class UserPicture {
    private static String name = "";

    public static String getPicture()
    {
        return name;
    }

    public static void setPicture(String name)
    {
        UserPicture.name = name;
    }
}
