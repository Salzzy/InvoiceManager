package de.format.salzzy.Rechnungsmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.format.salzzy.Rechnungsmanager.model.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	

}
