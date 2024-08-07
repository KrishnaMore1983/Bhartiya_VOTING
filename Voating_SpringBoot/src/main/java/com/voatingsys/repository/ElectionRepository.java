package com.voatingsys.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voatingsys.entity.Election;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {
    // You can define custom query methods here if needed

	
}
