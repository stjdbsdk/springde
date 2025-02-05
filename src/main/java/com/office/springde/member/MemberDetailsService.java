package com.office.springde.member;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MemberDetailsService implements UserDetailsService {

	final private MemberMapper memberMapper;
	
	public MemberDetailsService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername()");
		
		MemberDto selectedMemberDto = memberMapper.selectMemberByMId(username);
		
		if (selectedMemberDto != null) {
			
			return User.builder()
					.username(selectedMemberDto.getM_id())
					.password(selectedMemberDto.getM_pw())
					.roles(selectedMemberDto.getM_role())
					.build();
			
		}
		
		return null;
	}

}
