package com.example.fabienbrun.notepostit.user;

/**
 * Created by fabienbrun on 08/03/2017.
 */

public class User {
    private int id ;
    private String name;
    private String password;
    private String email;
    private Boolean logged;


    public User(int id,  String email, String name,  String password , Boolean logged ) {
        this.email = email;
        this.logged = logged;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getLogged() {
        return logged;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                ", id=" + id +
                "email='" + email + '\'' +
                ", logged=" + logged +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
