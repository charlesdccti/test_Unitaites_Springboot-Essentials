package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Departamento;
import com.example.repository.DepartamentoRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	public List<Departamento> findAll() {
		return departamentoRepository.findAll();
	}

	public Departamento findOne(Integer id) {
		return departamentoRepository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public Departamento save(Departamento entity) {
		return departamentoRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Departamento entity) {
		departamentoRepository.delete(entity);
	}

}
	
