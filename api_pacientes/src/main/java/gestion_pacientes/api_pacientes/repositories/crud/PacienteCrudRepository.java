package gestion_pacientes.api_pacientes.repositories.crud;

import gestion_pacientes.api_pacientes.models.Paciente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteCrudRepository extends CrudRepository<Paciente,Long> {
    Optional<List<Paciente>> findByNombres(String nombre);

}
