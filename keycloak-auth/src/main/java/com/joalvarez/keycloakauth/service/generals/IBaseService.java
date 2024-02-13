package com.joalvarez.keycloakauth.service.generals;

import java.util.List;

public interface IBaseService<DTO, PK> {

	List<DTO> findAll();
	DTO findById(PK id);
	DTO update(DTO dto);
	DTO create(DTO dto);
}
