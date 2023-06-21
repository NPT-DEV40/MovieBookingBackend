package com.backend.moviebooking.Service;

import com.backend.moviebooking.Model.Branch;

import java.util.List;

public interface IBranchService extends GenericService<Branch> {
    List<Branch> findBranchByBranchName(String branchName);

    List<Branch> getBranchByCityThatHasMovie(String city, String movieId);
}
