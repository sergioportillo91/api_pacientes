package gestion_pacientes.api_pacientes.services;

import gestion_pacientes.api_pacientes.models.Paciente;
import gestion_pacientes.api_pacientes.repositories.PacienteRepository;
import gestion_pacientes.api_pacientes.repositories.crud.PacienteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    /*public Optional<List<Paciente>> buscarPorNombre(String nombre){
        return  pacienteRepository.buscarPorNombre(nombre);
    }*/

    public List<Paciente> listar() {
        return (List<Paciente>) pacienteRepository.listar();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.buscarPorId(id);
    }

    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.guardar(paciente);
    }

    public void eliminar(Long id){
        pacienteRepository.eliminar(id);
    }

}
