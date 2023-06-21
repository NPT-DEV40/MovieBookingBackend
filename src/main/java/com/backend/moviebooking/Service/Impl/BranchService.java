package com.backend.moviebooking.Service.Impl;

import com.backend.moviebooking.Model.Branch;
import com.backend.moviebooking.Repository.BranchRepository;
import com.backend.moviebooking.Service.IBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService implements IBranchService {

    private final BranchRepository branchRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    @Override
    public void delete(String id) {
        branchRepository.deleteById(id);
    }

    @Override
    public Branch findById(String id) {
        return branchRepository.findById(id).orElse(null);
    }

    @Override
    public List<Branch> findBranchByBranchName(String branchName) {
        return branchRepository.findBranchByBranchName(branchName);
    }

    @Override
    public List<Branch> getBranchByCityThatHasMovie(String city, String movieId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("branch.id").in(
                Query.query(Criteria.where("schedule.movie.id").is(movieId)
                        .and("schedule.branch.city").is(city))
                        .fields().include("schedule.branch.id")));
        return mongoTemplate.find(query, Branch.class);
    }
}
