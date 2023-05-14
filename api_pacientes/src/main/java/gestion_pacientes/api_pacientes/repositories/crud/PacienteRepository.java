package gestion_pacientes.api_pacientes.repositories;

import gestion_pacientes.api_pacientes.models.Paciente;
import gestion_pacientes.api_pacientes.repositories.crud.PacienteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PacienteRepository {

    @Autowired
    private PacienteCrudRepository pacienteCrudRepository;
    public Optional<List<Paciente>> buscarPorNombre(String nombre){
        return  pacienteCrudRepository.findByNombres(nombre);
    }

    public List<Paciente> listar() {
        return (List<Paciente>) pacienteCrudRepository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteCrudRepository.findById(id);
    }

    public Paciente guardar(Paciente paciente) {
        return pacienteCrudRepository.save(paciente);
    }

    public void eliminar(Long id){
        pacienteCrudRepository.deleteById(id);
    }

}
