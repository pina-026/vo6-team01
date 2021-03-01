 
package com.example.api.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.api.UserRepository;

/**
 * User Entity
 */
@Service("winterUserDetailsService")
public class WinterUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public WinterUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        // emailでデータベースからユーザーエンティティを検索する
        return userRepository.findByEmail(email)
                .map(WinterLoginUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
