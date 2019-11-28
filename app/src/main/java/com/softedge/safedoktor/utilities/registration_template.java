package com.softedge.safedoktor.utilities;

public abstract class registration_template {

    abstract void verify_number();
    abstract void link_credentials();
    abstract void register_carewex();
    abstract void save_details_online();
    abstract void login_user();

    //registration template method
    public final void register(){

        //verify users phone number
        verify_number();

        //link phone number credentials to email
        link_credentials();

        //register user on carewex for OPD number
        register_carewex();

        //save user details to firebase database and local db
        save_details_online();

        //login user into account
        login_user();
    }
}
