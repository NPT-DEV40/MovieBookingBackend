package com.backend.moviebooking.Repository;

import com.backend.moviebooking.Model.Branch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends MongoRepository<Branch, String> {
    List<Branch> findBranchByBranchName(String branchName);

}
