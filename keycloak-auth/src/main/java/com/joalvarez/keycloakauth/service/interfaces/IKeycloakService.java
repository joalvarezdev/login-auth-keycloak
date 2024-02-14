package com.joalvarez.keycloakauth.service.interfaces;

import com.joalvarez.keycloakauth.data.dto.LoginDTO;
import com.joalvarez.keycloakauth.data.dto.LoginResponseDTO;
import com.joalvarez.keycloakauth.service.generals.IBaseService;

public interface IKeycloakService<DTO, PK> extends IBaseService<DTO, PK> {

	void delete(PK id);
	LoginResponseDTO login(LoginDTO dto);
}
