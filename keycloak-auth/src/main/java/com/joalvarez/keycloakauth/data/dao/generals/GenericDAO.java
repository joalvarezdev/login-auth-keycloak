package com.joalvarez.keycloakauth.data.dao.generals;

import com.joalvarez.keycloakauth.data.repository.generals.GenericRepository;

import java.util.List;

public abstract class GenericDAO<REP extends GenericRepository<ENT, PK>, ENT, PK> {

	protected final REP repository;

	public GenericDAO(REP repository) {
		this.repository = repository;
	}

	public List<ENT> findAll(){
		return this.repository.findAll();
	}

	public ENT findById(PK id){
		return this.repository.findById(id).orElse(null);
	}

	public ENT save(ENT entity){
		return this.repository.save(entity);
	}
}
