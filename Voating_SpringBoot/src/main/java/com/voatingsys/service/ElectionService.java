package com.voatingsys.service;

import java.util.List;

import com.voatingsys.entity.Election;

public interface ElectionService {
    
    List<Election> getAllElections();
    
    Election getElectionById(Long id);
    
    void saveElection(Election election);
    
    void deleteElection(Long id);

    List<Election> getUpcomingElections();

}

