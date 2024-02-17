package com.joalvarez.keycloakauth.data.dao;

import com.joalvarez.keycloakauth.data.dao.generals.GenericDAO;
import com.joalvarez.keycloakauth.data.models.Product;
import com.joalvarez.keycloakauth.data.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductDAO extends GenericDAO<ProductRepository, Product, Long> {
	public ProductDAO(ProductRepository repository) {
		super(repository);
	}
}
