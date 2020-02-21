package org.test;

import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void auth() {
        Login prueba = new Login();
        String actual = prueba.authtest("zascazasca95@gmail.com","1234");
        String expect = "8";
        assertEquals(expect,actual);
    }


    @Test
    void openHelp() throws JRException {
        FacturasView test2 = new FacturasView();
        boolean actual = test2.informar();
        boolean expect = true;
        assertEquals(expect,actual);
    }
}