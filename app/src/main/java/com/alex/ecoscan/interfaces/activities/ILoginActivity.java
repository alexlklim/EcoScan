package com.alex.ecoscan.interfaces.activities;

public interface ILoginActivity {

    void checkLoginAndPw(String login, String pw);

    // if user forget login and pw -> set default login and pw + clear all data
    void forgotLoginAndPw();

}
