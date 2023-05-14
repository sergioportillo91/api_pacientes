package gestion_pacientes.api_pacientes.controllers;

import gestion_pacientes.api_pacientes.models.Paciente;
import gestion_pacientes.api_pacientes.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        Optional<Paciente> user = pacienteService.buscarPorId(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> users = pacienteService.listar();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public ResponseEntity<Paciente> addPaciente(@RequestBody Paciente paciente) {
        Paciente newPaciente = pacienteService.guardar(paciente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPaciente.getId())
                .toUri();
        return ResponseEntity.created(location).body(newPaciente);
    }

    @PutMapping("/{id}")
    public Paciente updatePaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        Paciente updatedPaciente = pacienteService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "paciente not found"));
        updatedPaciente.setTipoIdentificacion(paciente.getTipoIdentificacion());
        updatedPaciente.setIdentificacion(paciente.getIdentificacion());
        updatedPaciente.setNombres(paciente.getNombres());
        updatedPaciente.setApellidos(paciente.getApellidos());
        updatedPaciente.setEdad(paciente.getEdad());
        return pacienteService.guardar(updatedPaciente);
    }

    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "paciente not found"));
        pacienteService.eliminar(id);
    }
}
