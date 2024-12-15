package com.library.service;

import com.library.model.Member;
import com.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // Get all members with filtering by name or email
    public List<Member> getAllMembers(String name, String email) {
        if (name != null && email != null) {
            return memberRepository.findByNameContainingAndEmailContaining(name, email);
        } else if (name != null) {
            return memberRepository.findByNameContaining(name);
        } else if (email != null) {
            return memberRepository.findByEmailContaining(email);
        } else {
            return memberRepository.findAll();
        }
    }

    // Add a new member
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    // Update an existing member
    public Member updateMember(Long id, Member member) {
        if (memberRepository.existsById(id)) {
            member.setId(id);
            return memberRepository.save(member);
        }
        return null;
    }

    // Delete a member
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}