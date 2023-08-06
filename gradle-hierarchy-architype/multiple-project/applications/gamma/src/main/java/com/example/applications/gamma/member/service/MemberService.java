package com.example.applications.gamma.member.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.cores.gamma.jpa.entity.Member;
import com.example.cores.gamma.jpa.entity.MembersAuthority;
import com.example.cores.gamma.jpa.repository.MemberRepository;
import com.example.cores.gamma.jpa.repository.MembersAuthorityRepository;

import lombok.RequiredArgsConstructor;

/**
 * MemberService
 * 
 * @author _sCream
 *
 */
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MembersAuthorityRepository membersAuthorityRepository;

    /**
     * retrieveMember:
     * 
     * @param conditionData
     * @return
     */
    public List<Member> retrieveMember(Member conditionData) {
        // todo
        return null;
    }

    /**
     * retrieveMember:
     * 
     * @param memberId
     * @return
     */
    public Member retrieveMember(String memberId) {
        // todo
        return null;
    }

    /**
     * addMember
     * 
     * @param requestData
     * @return
     */
    @Transactional
    public Member addMember(Member requestData) {
        // todo
        return null;
    }

    /**
     * modifyMember
     * 
     * @param patchData
     * @return
     */
    @Transactional
    public Member modifyMember(Member patchData) {
        // todo
        return null;
    }

    /**
     * removeMember:
     * 
     * @param memberId
     */
    @Transactional
    public void removeMember(String memberId) {
        // todo
    }

    /**
     * retrieveMembersAuthority:
     * 
     * @param conditionData
     * @return
     */
    public List<MembersAuthority> retrieveMembersAuthority(MembersAuthority conditionData) {
        // todo
        return null;
    }

    /**
     * retrieveMembersAuthority:
     * 
     * @param membersAuthorityId
     * @return
     */
    public MembersAuthority retrieveMembersAuthority(Integer membersAuthorityId) {
        // todo
        return null;
    }

    /**
     * addMembersAuthority:
     * 
     * @param requestData
     * @return
     */
    public MembersAuthority addMembersAuthority(MembersAuthority requestData) {
        // todo
        return null;
    }

    /**
     * modifyMembersAuthority:
     * 
     * @param patchData
     * @return
     */
    public MembersAuthority modifyMembersAuthority(MembersAuthority patchData) {
        // todo
        return null;
    }

    /**
     * removeMembersAuthority:
     * 
     * @param membersAuthorityId
     */
    public void removeMembersAuthority(Integer membersAuthorityId) {
        // todo
    }
}
