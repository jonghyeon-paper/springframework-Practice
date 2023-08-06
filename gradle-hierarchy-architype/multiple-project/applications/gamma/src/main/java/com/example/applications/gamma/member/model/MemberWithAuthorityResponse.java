package com.example.applications.gamma.member.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MemberWithAuthorityResponse
 * 
 * @author _sCream
 *
 */
@Data
@NoArgsConstructor
public class MemberWithAuthorityResponse {

    private String memberId;
    private String password;
    private String name;
    private Integer age;
    private List<String> hasAuthorityList;
}
