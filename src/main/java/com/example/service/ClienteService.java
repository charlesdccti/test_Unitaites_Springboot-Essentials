package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Cliente;
import com.example.repository.ClienteRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findOne(Integer id) {
		return clienteRepository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public Cliente save(Cliente entity) {
		return clienteRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Cliente entity) {
		clienteRepository.delete(entity);
	}

}
	
