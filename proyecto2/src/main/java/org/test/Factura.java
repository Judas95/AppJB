package org.test;

import java.time.LocalDate;
import java.util.Date;

public class Factura {

        private int Idfactura;
        private String Cliente;
        private LocalDate Facturado;
        private LocalDate Vencimiento;
        private double   Precio;
        private double  Total;
        private int Idcliente;

    public Factura() {
    }

    public Factura(int idfactura, String cliente, LocalDate facturado, LocalDate vencimiento, double precio, double total, int idcliente) {
        Idfactura = idfactura;
        Cliente = cliente;
        Facturado = facturado;
        Vencimiento = vencimiento;
        Precio = precio;
        Total = total;
        Idcliente = idcliente;
    }

    public int getIdfactura() {
        return Idfactura;
    }

    public void setIdfactura(int idfactura) {
        Idfactura = idfactura;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public LocalDate getFacturado() {
        return Facturado;
    }

    public void setFacturado(LocalDate facturado) {
        Facturado = facturado;
    }

    public LocalDate getVencimiento() {
        return Vencimiento;
    }

    public void setVencimiento(LocalDate vencimiento) {
        Vencimiento = vencimiento;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public int getIdcliente() {
        return Idcliente;
    }

    public void setIdcliente(int idcliente) {
        Idcliente = idcliente;
    }
}
