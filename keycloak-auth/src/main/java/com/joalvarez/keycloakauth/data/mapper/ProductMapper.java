package com.joalvarez.keycloakauth.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.keycloakauth.data.dto.ProductDTO;
import com.joalvarez.keycloakauth.data.mapper.generals.BaseMapper;
import com.joalvarez.keycloakauth.data.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends BaseMapper<ProductDTO, Product> {

	public ProductMapper(ObjectMapper objectMapper) {
		super(objectMapper);
	}
}
