package com.library.controller;

import com.library.model.Member;
import com.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin("http://localhost:3000")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // List all members with optional filtering by name and email
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email) {

        List<Member> members = memberService.getAllMembers(name, email);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    // Add a new member
    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member createdMember = memberService.addMember(member);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    // Update an existing member
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable("id") Long id, @RequestBody Member member) {
        Member updatedMember = memberService.updateMember(id, member);
        if (updatedMember != null) {
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a member
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}