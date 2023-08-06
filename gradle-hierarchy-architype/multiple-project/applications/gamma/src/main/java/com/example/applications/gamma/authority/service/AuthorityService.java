package com.example.applications.gamma.authority.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cores.gamma.jpa.entity.Authority;
import com.example.cores.gamma.jpa.entity.AuthoritysFunction;
import com.example.cores.gamma.jpa.repository.AuthorityRepository;
import com.example.cores.gamma.jpa.repository.AuthoritysFunctionRepository;

import lombok.RequiredArgsConstructor;

/**
 * AuthorityService
 * 
 * @author _sCream
 *
 */
@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final AuthoritysFunctionRepository authoritysFunctionRepository;

    /**
     * retrieveAuthority:
     * 
     * @param conditionData
     * @return
     */
    public List<Authority> retrieveAuthority(Authority conditionData) {
        // todo
        return null;
    }

    /**
     * retrieveAuthority:
     * 
     * @param authorityId
     * @return
     */
    public Authority retrieveAuthority(String authorityId) {
        // todo
        return null;
    }

    /**
     * addAuthority:
     * 
     * @param requestData
     * @return
     */
    public Authority addAuthority(Authority requestData) {
        // todo
        return null;
    }

    /**
     * modifyAuthority:
     * 
     * @param patchData
     * @return
     */
    public Authority modifyAuthority(Authority patchData) {
        // todo
        return null;
    }

    /**
     * removeAuthority:
     * 
     * @param authorityId
     */
    public void removeAuthority(String authorityId) {
        // todo
    }

    /**
     * retrieveAuthoritysFunction:
     * 
     * @param conditionData
     * @return
     */
    public List<AuthoritysFunction> retrieveAuthoritysFunction(AuthoritysFunction conditionData) {
        // todo
        return null;
    }

    /**
     * retrieveAuthoritysFunction:
     * 
     * @param authoritysFunctionId
     * @return
     */
    public AuthoritysFunction retrieveAuthoritysFunction(Integer authoritysFunctionId) {
        // todo
        return null;
    }

    /**
     * addAuthoritysFunction:
     * 
     * @param requestData
     * @return
     */
    public AuthoritysFunction addAuthoritysFunction(AuthoritysFunction requestData) {
        // todo
        return null;
    }

    /**
     * modifyAuthoritysFunction:
     * 
     * @param patchData
     * @return
     */
    public AuthoritysFunction modifyAuthoritysFunction(AuthoritysFunction patchData) {
        // todo
        return null;
    }

    /**
     * removeAuthoritysFunction:
     * 
     * @param authoritysFunctionId
     */
    public void removeAuthoritysFunction(Integer authoritysFunctionId) {
        // todo
    }
}
