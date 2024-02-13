package com.joalvarez.keycloakauth.service.generals;

public abstract class GenericService<DAO, MAP> {

	protected final MAP mapper;
	protected final DAO dao;

	public GenericService(DAO dao, MAP mapper) {
		this.dao = dao;
		this.mapper = mapper;
	}

}
