package com.example.demo.test_utils;

import com.example.demo.entity.SportObject;
import com.example.demo.entity.User;
import com.example.demo.utils.SportObjectType;

public class TestUtils
{
    public static SportObject sampleValidSportObject() {
        return new SportObject(
                "Mosir",
                "Bursaki 40",
                "Krosno",
                SportObjectType.ORLIK);
    }

    public static User sampleValidUser() {
        return new User(
                "sebaPL", "Qwer123#$", "seba@galecki.pl"
        );
    }
}
