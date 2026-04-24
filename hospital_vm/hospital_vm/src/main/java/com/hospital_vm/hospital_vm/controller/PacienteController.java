package com.hospital_vm.hospital_vm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital_vm.hospital_vm.modelo.Paciente;
import com.hospital_vm.hospital_vm.service.PacienteService;

@RestController
@RequestMapping ("api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List <Paciente>> listar() {
        List<Paciente> pacientes = pacienteService.findAll();
        if(pacientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);

    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        Paciente nuevoPaciente = pacienteService.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
        }

    //Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> ObtenerPorId(@PathVariable Integer id ) {
        try {
            Paciente paciente = pacienteService.findById(id);
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    
  }

  //Actualizar
  @PutMapping ("/{id}")
  public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Integer id, @RequestBody Paciente paciente) {
    try{
        Paciente actualPaciente = pacienteService.findById(id);
        actualPaciente.setId(id);
        actualPaciente.setRun(paciente.getRun());
        actualPaciente.setNombres(paciente.getNombres());
        actualPaciente.setApellidos(paciente.getApellidos());
        actualPaciente.setFechaNacimiento(paciente.getFechaNacimiento());
        actualPaciente.setCorreo(paciente.getCorreo());

        pacienteService.save(actualPaciente);
        return ResponseEntity.ok(paciente);
        
    }catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id){
    try{
        pacienteService.findById(id);
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
        
    }catch (Exception e) {
        return ResponseEntity.notFound().build();
    }

  }

}