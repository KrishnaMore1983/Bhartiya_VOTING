package com.voatingsys.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voatingsys.entity.Voter;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Integer> {
	
	Voter findByUsernameAndPassword(String username, String password);

	Voter findByUsername(String username);
}
