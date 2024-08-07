package com.voatingsys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatingsys.entity.Voter;
import com.voatingsys.repository.VoterRepository;


@Service
public class VoterServiceImpl implements VoterService{

	@Autowired
	private VoterRepository voterRepo;
	
//	@Override
//	public void saveVoter(Voter voter) {
//		voterRepo.save(voter);
//	}
	
	@Override
	public List<Voter> voterlist(){
		return voterRepo.findAll();
	}
	
	@Override
	public Voter findByUsernameAndPassword(String username,String password) {
		return voterRepo.findByUsernameAndPassword(username, password);
	}
	
	 @Override
	    public List<Voter> getAllVoters() {
	        return voterRepo.findAll();
	    }

	    @Override
	    public Voter getVoterById(Integer id) {
	        return voterRepo.findById(id).orElseThrow();
	    }

	    @Override
	    public void deleteVoter(Integer id) {
	        voterRepo.deleteById(id);
	    }

	  
	    @Override
	    public void saveVoter(Voter voter) {
	        int id = voter.getId();
	        if (id != 0) {
	            // Update existing voter
	            if (voterRepo.existsById(id)) {
	                Voter existingVoter = voterRepo.findById(id)
	                    .orElseThrow(() -> new RuntimeException("Voter not found"));
	                existingVoter.setName(voter.getName());
	                existingVoter.setAddress(voter.getAddress());
	                existingVoter.setDOB(voter.getDOB());
	                existingVoter.setGender(voter.getGender());
	             //   existingVoter.setUsername(voter.getUsername());
	             // existingVoter.setPassword(voter.getPassword());
	                voterRepo.save(existingVoter);
	            } else {
	                throw new RuntimeException("Voter with ID " + id + " does not exist.");
	            }
	        } else {
	            // Create new voter
	            voterRepo.save(voter);
	        }
	    }
//	    @Override
//	    public Voter getVoter(Integer id) {
	        // Your implementation here
//	        return null; // Replace with actual logic
//	    }

//	    @Override
//	    public Voter findByUsername(String username) {
//	        return voterRepo.findByUsername(username); // Fetch voter details from the database
	   
	

}
