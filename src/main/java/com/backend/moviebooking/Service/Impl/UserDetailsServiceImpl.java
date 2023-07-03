package com.backend.moviebooking.Service.Impl;

import com.backend.moviebooking.Model.Enum.Provider;
import com.backend.moviebooking.Model.User;
import com.backend.moviebooking.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }

    public List<User> findAllDescByRoles() {
        Query query = new Query();
        query.with(Sort.sort(User.class).by(User::getRoles).descending());
        return mongoTemplate.find(query, User.class);
    }

    public void processOAuthPostLogin(String username) {
        User user = userRepository.getUserByUsername(username);
        if(user == null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setProvider(Provider.GOOGLE);
            userRepository.save(newUser);
        }
    }
}
