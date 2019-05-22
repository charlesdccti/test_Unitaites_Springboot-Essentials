package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.model.Departamento;
import com.example.service.DepartamentoService;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

	private static final String MSG_SUCESS_INSERT = "Departamento inserted successfully.";
	private static final String MSG_SUCESS_UPDATE = "Departamento successfully changed.";
	private static final String MSG_SUCESS_DELETE = "Deleted Departamento successfully.";
	private static final String MSG_ERROR = "Error.";

	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping
	public List<Departamento> index(Model model) {
		List<Departamento> all = departamentoService.findAll();
		model.addAttribute("listDepartamento", all);
		return all;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Departamento> show(Model model, @PathVariable("id") Integer id) {
		Departamento departamentoSaved = null;
		if (id != null) {
			departamentoSaved = departamentoService.findOne(id);
			model.addAttribute("departamento", departamentoSaved);
			
			HttpStatus status = HttpStatus.CREATED;
			return new ResponseEntity<>(departamentoSaved, status);
		}
		HttpStatus status = HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(departamentoSaved, status);
	}

	@GetMapping(value = "/new")
	public String create(Model model, @ModelAttribute Departamento entity) {
		model.addAttribute("departamento", entity);
		return "departamento/form";
	}
	
	@PostMapping
	public String create(@Valid @ModelAttribute Departamento entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Departamento departamento = null;
		try {
			departamento = departamentoService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/departamentos/" + departamento.getId();
	}
	
	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		try {
			if (id != null) {
				Departamento entity = departamentoService.findOne(id);
				model.addAttribute("departamento", entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "departamento/form";
	}
	
	@PutMapping
	public String update(@Valid @ModelAttribute Departamento entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Departamento departamento = null;
		try {
			departamento = departamentoService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/departamentos/" + departamento.getId();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Departamento entity = departamentoService.findOne(id);
				departamentoService.delete(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/departamentos/index";
	}

}
