package es.jump2digital.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.jump2digital.model.domain.Company;
import es.jump2digital.model.domain.PerFoundedDto;
import es.jump2digital.model.domain.PerIndustryDto;
import es.jump2digital.model.domain.PerSizeDto;
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
	// http://localhost:8080/orderBySize
	@GetMapping("/orderBySize")
	public ResponseEntity<?> bySize() {
		ResponseEntity<?> response = null;
		List<Company> allCompanies = companies.findAll();
		Collections.sort(allCompanies, Company.SizeCompanyComparator);
		try {
			response = new ResponseEntity<List<Company>>(allCompanies, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;

	}

	// Crear un endpoint que devuelva las compañías ordenadas por fecha de creación
	// http://localhost:8080/orderByFounded

	@GetMapping("/orderByFounded")
	public ResponseEntity<?> byFounded() {
		ResponseEntity<?> response = null;
		List<Company> allCompanies = companies.findAll();
		Collections.sort(allCompanies, Company.FoundedCompanyComparator);
		try {
			response = new ResponseEntity<List<Company>>(allCompanies, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;

	}
	// Crear un endpoint que devuelva los siguientes datos: Número
	// de empresas que hay en cada industria, Número de empresas que
	// hay por cada rango de tamaños, Número de empresas que hay en cada año de
	// creación

	// http://localhost:8080/IndustrySizeFounded
	@GetMapping("/IndustrySizeFounded")
	public ResponseEntity<?> numberPerIndustry() {
		ResponseEntity<?> response = null;
		List<Company> allCompanies = companies.findAll();
		int j = 0, y = 0, k = 0, limit = allCompanies.size() - 1;

		ArrayList<PerIndustryDto> countIndustry = new ArrayList<PerIndustryDto>();
		ArrayList<PerSizeDto> countSize = new ArrayList<PerSizeDto>();
		ArrayList<PerFoundedDto> countFounded = new ArrayList<PerFoundedDto>();

		Collections.sort(allCompanies, Company.FoundedCompanyComparator);
		List<Company> auxOrderIndustry = allCompanies;
		Collections.sort(allCompanies, Company.SizeCompanyComparator);
		List<Company> auxOrderSize = allCompanies;
		Collections.sort(allCompanies, Company.IndustryCompanyComparator);
		List<Company> auxOrderFounded = allCompanies;
		PerSizeDto perSize;
		PerFoundedDto perFounded;
		PerIndustryDto perIndustry;
		for (int i = 0; i < limit - 1; i++) {
			j++;
			y++;
			k++;
			if (auxOrderSize.get(i).sizeToInt() != auxOrderSize.get(i + 1).sizeToInt()) {
				perSize = new PerSizeDto(auxOrderSize.get(i).getSize(), j);
				countSize.add(perSize);
				j = 0;
			}
			if (auxOrderFounded.get(i).getFounded() != auxOrderFounded.get(i + 1).getFounded()) {
				perFounded = new PerFoundedDto(auxOrderFounded.get(i).getFounded(), y);
				countFounded.add(perFounded);
				y = 0;
			}
			if (!(auxOrderIndustry.get(i).getIndustry().equalsIgnoreCase(auxOrderIndustry.get(i + 1).getIndustry()))) {
				perIndustry = new PerIndustryDto(auxOrderIndustry.get(i).getIndustry(), k);
				countIndustry.add(perIndustry);
				k = 0;
			}

			if (i == limit - 2) {
				perSize = new PerSizeDto(auxOrderSize.get(i).getSize(), j + 2);
				countSize.add(perSize);
				perFounded = new PerFoundedDto(auxOrderFounded.get(i).getFounded(), y + 2);
				countFounded.add(perFounded);
				perIndustry = new PerIndustryDto(auxOrderIndustry.get(i).getIndustry(), k + 2);
				countIndustry.add(perIndustry);
			}

		}

		ArrayList<ArrayList<?>> arraylists = new ArrayList<ArrayList<?>>();
		arraylists.add(countIndustry);
		arraylists.add(countSize);
		arraylists.add(countFounded);

		try {
			response = new ResponseEntity<ArrayList<ArrayList<?>>>(arraylists, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;

	}
}