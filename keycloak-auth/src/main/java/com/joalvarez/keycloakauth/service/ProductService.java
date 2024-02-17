package com.joalvarez.keycloakauth.service;

import com.joalvarez.keycloakauth.data.dao.ProductDAO;
import com.joalvarez.keycloakauth.data.dto.ProductDTO;
import com.joalvarez.keycloakauth.data.mapper.ProductMapper;
import com.joalvarez.keycloakauth.service.generals.GenericService;
import com.joalvarez.keycloakauth.service.interfaces.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService extends GenericService<ProductDAO, ProductMapper> implements IProductService {

	public ProductService(ProductDAO productDAO, ProductMapper mapper) {
		super(productDAO, mapper);
	}

	@Override
	public List<ProductDTO> findAll() {
		return this.dao.findAll().stream()
			.map(this.mapper::toDTO)
			.toList();
	}

	@Override
	public ProductDTO findById(Long id) {
		return null;
	}

	@Override
	public ProductDTO update(ProductDTO productDTO) {
		return null;
	}

	@Override
	public ProductDTO create(ProductDTO productDTO) {
		return null;
	}
}
