package com.local.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.local.entidades.Persona;

@Repository
public interface IPersona extends CrudRepository<Persona, Integer> {

}
