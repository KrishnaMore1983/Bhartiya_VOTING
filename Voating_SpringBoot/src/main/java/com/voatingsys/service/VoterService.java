package com.voatingsys.service;

import java.util.List;


import com.voatingsys.entity.Voter;

public interface VoterService {

	 void saveVoter(Voter voter);
	    List<Voter> voterlist();
	    Voter findByUsernameAndPassword(String username, String password);
		
	    List<Voter> getAllVoters();

	    Voter getVoterById(Integer id);

	    void deleteVoter(Integer id);
	   
	    void saveVoter();
		//List<Voter> getAllVoters();
	//	Voter getVoterProfileByUsername(String username);
	
	//	Voter getVoter(String username);
		
	//	Voter findByUsername(String username);
		
	
}
