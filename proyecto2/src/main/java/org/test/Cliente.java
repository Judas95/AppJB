package org.test;

public class Cliente {
    private int idcliente;
    private String name;
    private String website;
    private String street;
    private String vat;
    private String phone;
    private String email;

    public Cliente(int idcliente, String name, String website, String street, String vat, String phone, String email) {
        this.idcliente = idcliente;
        this.name = name;
        this.website = website;
        this.street = street;
        this.vat = vat;
        this.phone = phone;
        this.email = email;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
