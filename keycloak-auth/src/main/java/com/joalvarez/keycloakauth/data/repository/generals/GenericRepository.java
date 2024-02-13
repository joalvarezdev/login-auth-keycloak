package com.joalvarez.keycloakauth.data.repository.generals;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<ENT, PK> {
	List<ENT> findAll();
	Optional<ENT> findById(PK id);
	ENT save(ENT entity);
}
