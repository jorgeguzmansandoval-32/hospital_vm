package com.hospital_vm.hospital_vm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital_vm.hospital_vm.modelo.Paciente;

@Repository

public interface PacienteRepository extends JpaRepository<Paciente, Long> {



}
