package test;

import dao.impl.DaoEnMemoria;
import model.Veterinario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.VeterinarioService;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VeterinarioServiceTestMemoria {
    VeterinarioService veterinarioService = new VeterinarioService(new DaoEnMemoria());

    @Test
    @DisplayName("Testear que un veterinario fue cargado correctamente")
    void caso1(){
        //Dado
        Veterinario veterinario = new Veterinario("CEPL4565678","LUCIA", "CERPA", "CANINOS");
        //cuando
        Veterinario veterinarioDesdeDb = veterinarioService.guardarVeterinario(veterinario);
        // entonces
        assertNotNull(veterinarioDesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que se obtengan todos los veterinarios")
    void caso2(){
        //Dado
        Integer id = 1;
        //cuando
        Veterinario veterinario = new Veterinario("RHES4568","ROBERTO", "OROZCO", "FELINOS");
        //cuando
        veterinarioService.guardarVeterinario(veterinario);
        List<Veterinario> veterinariosDesdeDb = veterinarioService.listarVeterinarios();
        // entonces
        assertFalse(veterinariosDesdeDb.isEmpty());
    }

}