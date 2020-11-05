package com.local.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.local.entidades.Departamento;

@Repository
public interface IDepartamento extends CrudRepository<Departamento, Integer> {

}
