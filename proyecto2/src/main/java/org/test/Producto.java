package org.test;

public class Producto {
    private int preciounidad;
    private int cantidad;
    private String descripcion;
    private int idproducto;
    private float preciofinal;
    private int invoiceLineId;
    private String name;

    public Producto() {
    }

    public Producto(int preciounidad, int cantidad, String descripcion, int idproducto, float preciofinal, int invoiceLineId, String name) {
        this.preciounidad = preciounidad;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.idproducto = idproducto;
        this.preciofinal = preciofinal;
        this.invoiceLineId = invoiceLineId;
        this.name = name;
    }

    public int getPreciounidad() {
        return preciounidad;
    }

    public void setPreciounidad(int preciounidad) {
        this.preciounidad = preciounidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public float getPreciofinal() {
        return preciofinal;
    }

    public void setPreciofinal(float preciofinal) {
        this.preciofinal = preciofinal;
    }

    public int getInvoiceLineId() {
        return invoiceLineId;
    }

    public void setInvoiceLineId(int invoiceLineId) {
        this.invoiceLineId = invoiceLineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

