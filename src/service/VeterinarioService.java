package service;

import dao.IDao;
import model.Veterinario;

import java.util.List;

public class VeterinarioService {
    private final IDao<Veterinario> veterinarioIDao;

    public VeterinarioService(IDao<Veterinario> veterinarioIDao) {
        this.veterinarioIDao = veterinarioIDao;
    }

    public Veterinario guardarVeterinario(Veterinario veterinario){
        return veterinarioIDao.guardar(veterinario);
    }

    public List<Veterinario> listarVeterinarios(){
        return veterinarioIDao.listaTodos();
    }
}
