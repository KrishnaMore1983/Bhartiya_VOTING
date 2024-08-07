package com.voatingsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatingsys.entity.Election;
import com.voatingsys.repository.ElectionRepository;

@Service
public class ElectionServiceImpl implements ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    @Override
    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    @Override
    public Election getElectionById(Long id) {
        return electionRepository.findById(id).orElse(null);
    }

    @Override
    public void saveElection(Election election) {
        electionRepository.save(election);
    }

    @Override
    public void deleteElection(Long id) {
        electionRepository.deleteById(id);
    }
    @Override
    public List<Election> getUpcomingElections() {
        // You can modify this method to fetch specific elections based on your needs
        return electionRepository.findAll(); // Or use a custom query to filter upcoming elections
    }
    
}
