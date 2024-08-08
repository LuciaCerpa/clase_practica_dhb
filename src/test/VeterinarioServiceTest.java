package test;

import dao.impl.DaoH2;
import model.Veterinario;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.VeterinarioService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VeterinarioServiceTest {
    static final Logger logger = Logger.getLogger(VeterinarioServiceTest.class);
    VeterinarioService veterinarioService = new VeterinarioService(new DaoH2());
    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./prueba_dh2;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }
    @Test
    @DisplayName("Testear que un veterinario fue cargado correctamente")
    void caso1(){
        //Dado
        Veterinario veterinario = new Veterinario("CER45648","LUCIA", "CERPA", "CANINOS");
        //cuando
        Veterinario veterinarioDesdeDb = veterinarioService.guardarVeterinario(veterinario);
        // entonces
        assertNotNull(veterinarioDesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que muestre todos los veterinarios")
    void caso2(){
        //Dado
        Integer id = 1;
        //cuando
        List<Veterinario> veterinariosDesdeDb = veterinarioService.listarVeterinarios();
        // entonces
        assertFalse(veterinariosDesdeDb.isEmpty());
    }



}