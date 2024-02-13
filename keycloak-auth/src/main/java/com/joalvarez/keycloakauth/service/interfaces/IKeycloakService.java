package com.joalvarez.keycloakauth.service.interfaces;

import com.joalvarez.keycloakauth.service.generals.IBaseService;

public interface IKeycloakService<DTO, PK> extends IBaseService<DTO, PK> {

	void delete(PK id);
}
