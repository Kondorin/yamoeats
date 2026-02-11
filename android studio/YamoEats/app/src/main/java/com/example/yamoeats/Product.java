package com.example.yamoeats;

public class Product {
    private int nopedido;
    private int id;
    private String name;
    private String price;
    private String description;
    private String direccion;
    private String sabor;
    private String usuario;
    private String celular;
    //constructor

    public Product(int id, String name, String price, String description, String celular) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.celular = celular;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    //setter y getter


    public int getNopedido() {
        return nopedido;
    }

    public void setNopedido(int nopedido) {
        this.nopedido = nopedido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
