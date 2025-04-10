package com.nhnacademy.auth.service;

import com.nhnacademy.auth.adaptor.MemberAdaptor;
import com.nhnacademy.auth.dto.LoginResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MemberDetailService implements UserDetailsService {
    /**
     * 회원 정보를 가져오는 adaptor.
     */
    private final MemberAdaptor memberAdaptor;

    public MemberDetailService(MemberAdaptor memberAdaptor) {
        this.memberAdaptor = memberAdaptor;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginResponse loginResponse = memberAdaptor.getLoginInfo(username);
        if (loginResponse == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        return new MemberDetails(loginResponse);
    }
}
