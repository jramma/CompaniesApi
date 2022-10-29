package es.jump2digital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



import es.jump2digital.model.domain.Company;
import es.jump2digital.model.repository.CompanyRepository;

@RestController
public class CompanyController {

	@Autowired
	CompanyRepository companies;

	// http://localhost:8080/getAll
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllCompanies() {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<List<Company>>(companies.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;

	}

	// Crear un endpoint que devuelva las compañías ordenadas por tamaño
	// http://localhost:8080/getOrderedBySize
	@GetMapping("/getOrderedBySize")
	public ResponseEntity<?> bySize() {
		ResponseEntity<?> response = null;
		Company aux;
		List<Company> allCompanies = companies.findAll();
		for (int i = 0; allCompanies.size() - 1 > i; i++) {
			if (allCompanies.get(i).sizeToInt() > allCompanies.get(i + 1).sizeToInt()) {
				aux = allCompanies.get(i);
				allCompanies.set(i, allCompanies.get(i + 1));
				allCompanies.set(i + 1, aux);
			}
		}

		try {
			response = new ResponseEntity<List<Company>>(allCompanies, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;

	}

	// Crear un endpoint que devuelva las compañías ordenadas por fecha de creación
	// Crear un endpoint que devuelva los siguientes datos: Número
	// de empresas que hay en cada industria, Número de empresas que
	// hay por cada rango de tamaños, Número de empresas que hay en cada año de
	// creación
}
