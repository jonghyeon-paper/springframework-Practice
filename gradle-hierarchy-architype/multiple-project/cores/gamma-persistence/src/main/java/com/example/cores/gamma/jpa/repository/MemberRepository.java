package com.example.cores.gamma.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cores.gamma.jpa.entity.Member;

/**
 * MemberRepository
 * 
 * @author _sCream
 * 
 */
public interface MemberRepository extends JpaRepository<Member, String> {

}
