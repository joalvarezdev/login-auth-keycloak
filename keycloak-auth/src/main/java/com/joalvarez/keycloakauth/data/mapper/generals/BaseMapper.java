package com.joalvarez.keycloakauth.data.mapper.generals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.keycloakauth.data.dto.generals.BaseDTO;
import org.springframework.core.GenericTypeResolver;

import java.util.Objects;
public abstract class BaseMapper<DTO extends BaseDTO, ENT> {

	private Class<DTO> dtoClass;
	private Class<ENT> domainClass;
	protected final ObjectMapper objectMapper;
	@SuppressWarnings("unchecked")
	public BaseMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		Class<?>[] arguments = GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseMapper.class);
		if (Objects.nonNull(arguments) && arguments.length > 0) {
			this.dtoClass = (Class<DTO>) arguments[0];
			this.domainClass = (Class<ENT>) arguments[1];
		}
	}

	public DTO toDTO(ENT entity) {
		return this.objectMapper.convertValue(entity, this.dtoClass);
	}

	public ENT fromDTO(DTO entity) {
		return this.objectMapper.convertValue(entity, this.domainClass);
	}
}
