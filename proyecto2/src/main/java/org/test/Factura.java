package org.test;

import java.util.Date;

public class Factura {

        private int id;
        private int fecha;
        private int total;
        private int sinimpuestos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSinimpuestos() {
        return sinimpuestos;
    }

    public void setSinimpuestos(int sinimpuestos) {
        this.sinimpuestos = sinimpuestos;
    }

    public int getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(int impuestos) {
        this.impuestos = impuestos;
    }

    public Factura(int id, int fecha, int total, int sinimpuestos, int impuestos) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.sinimpuestos = sinimpuestos;
        this.impuestos = impuestos;
    }

    private int impuestos;


}
