package es.jump2digital.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.jump2digital.model.domain.Company;


public interface CompanyRepository extends MongoRepository <Company, Integer> {

}
