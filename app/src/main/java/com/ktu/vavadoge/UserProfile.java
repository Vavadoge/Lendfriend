package com.ktu.vavadoge;

public class UserProfile
{
    private static String name = "";

    public static String getName()
    {
        return name;
    }

    public static void setName(String name)
    {
        UserProfile.name = name;
    }

}
