package org.test;

public class Cliente {
    public String image;
    public String website;
    public String phone;
    public String street;
    public String name;
    public String vat;
    public int idcliente;
    public String email;

    public Cliente() {
    }

    public Cliente(String image, String website, String phone, String street, String name, String vat, int idcliente, String email) {
        this.image = image;
        this.website = website;
        this.phone = phone;
        this.street = street;
        this.name = name;
        this.vat = vat;
        this.idcliente = idcliente;
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
