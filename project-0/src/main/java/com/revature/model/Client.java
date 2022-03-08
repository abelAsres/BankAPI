package com.revature.model;

import java.util.Objects;

//follow java bean standards ensuring the class is encapsulated (https://en.wikipedia.org/wiki/JavaBeans)
public class Client {
    private int id;
    private String first_name;
    private String last_name;

    public Client(){}

    public Client(int id, String first_name, String last_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    //Override equals, hashCode, and toString methods to ensure the comparison is done based on objects properties
    //instead of reference variables
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(first_name, client.first_name) && Objects.equals(last_name, client.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
