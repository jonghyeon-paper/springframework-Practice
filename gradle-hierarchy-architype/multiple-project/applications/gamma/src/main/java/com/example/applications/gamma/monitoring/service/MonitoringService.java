package com.example.applications.gamma.monitoring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.applications.gamma.authority.model.AuthorityWithFunctionResponse;
import com.example.applications.gamma.function.model.FunctionHierarchy;
import com.example.applications.gamma.member.model.MemberWithAuthorityResponse;
import com.example.applications.gamma.misc.HierarchyProcessor;
import com.example.cores.gamma.jpa.constant.FunctionType;
import com.example.cores.gamma.jpa.entity.AuthoritysFunction;
import com.example.cores.gamma.jpa.entity.Function;
import com.example.cores.gamma.jpa.entity.MembersAuthority;
import com.example.cores.gamma.jpa.repository.AuthorityRepository;
import com.example.cores.gamma.jpa.repository.AuthorityWithRelationRepository;
import com.example.cores.gamma.jpa.repository.AuthoritysFunctionRepository;
import com.example.cores.gamma.jpa.repository.FunctionRepository;
import com.example.cores.gamma.jpa.repository.MemberRepository;
import com.example.cores.gamma.jpa.repository.MembersAuthorityRepository;

import lombok.RequiredArgsConstructor;

/**
 * MonitoringService
 * 
 * @author _sCream
 *
 */
@Service
@RequiredArgsConstructor
public class MonitoringService {

    private final MemberRepository memberRepository;
    private final MembersAuthorityRepository membersAuthorityRepository;
    private final AuthorityRepository authorityRepository;
    private final AuthoritysFunctionRepository authoritysFunctionRepository;
    private final FunctionRepository functionRepository;
    private final AuthorityWithRelationRepository authorityWithRelationRepository;

    /**
     * retrieveDatabase:
     * 
     * @return
     */
    public Map<String, Object> retrieveDatabase() {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("member", memberRepository.findAll());
        responseData.put("membersAuthority", membersAuthorityRepository.findAll());
        responseData.put("authority", authorityRepository.findAll());
        responseData.put("authoritysFunction", authoritysFunctionRepository.findAll());
        responseData.put("function", functionRepository.findAll());
        return responseData;
    }

    /**
     * retrieveMemberWithAothority:
     * 
     * @return
     */
    public List<?> retrieveMemberWithAothority() {
        return memberRepository.findAll().stream()
                .map(item -> {
                    MembersAuthority conditionData = new MembersAuthority();
                    conditionData.setMemberId(item.getMemberId());
                    List<MembersAuthority> foundMembersAuthorityList = membersAuthorityRepository.findAll(Example.of(conditionData));

                    MemberWithAuthorityResponse returnItem = new MemberWithAuthorityResponse();
                    BeanUtils.copyProperties(item, returnItem);
                    returnItem.setHasAuthorityList(foundMembersAuthorityList.stream()
                            .map(MembersAuthority::getAuthorityId)
                            .collect(Collectors.toList()));

                    return returnItem;
                }).collect(Collectors.toList());
    }

    /**
     * retrieveAuthorityWithFunction:
     * 
     * @return
     */
    public List<?> retrieveAuthorityWithFunction() {
        return authorityWithRelationRepository.findAll().stream()
                .map(item -> {
                    List<Integer> functionIdList = item.getAuthoritysFunctionList().stream()
                            .map(AuthoritysFunction::getFunctionId)
                            .toList();
                    List<Function> functionList = functionRepository.findAllById(functionIdList);

                    List<AuthorityWithFunctionResponse.Function> apiFunctionList = functionList.stream()
                            .filter(functionItem -> FunctionType.API == functionItem.getType())
                            .map(functionItem -> {
                                AuthorityWithFunctionResponse.Function returnItem = new AuthorityWithFunctionResponse.Function();
                                BeanUtils.copyProperties(functionItem, returnItem);
                                return returnItem;
                            })
                            .toList();

                    List<FunctionHierarchy> menuFunctionList = functionList.stream()
                            .filter(functionItem -> FunctionType.UI == functionItem.getType())
                            .map(functionItem -> {
                                FunctionHierarchy returnItem = new FunctionHierarchy();
                                BeanUtils.copyProperties(functionItem, returnItem);
                                return returnItem;
                            })
                            .collect(Collectors.toList());

                    FunctionHierarchy root = new FunctionHierarchy();
                    root.setName("null-root");
                    root.setFunctionId(null);
                    root = HierarchyProcessor.transform(root, menuFunctionList);

                    AuthorityWithFunctionResponse returnItem = new AuthorityWithFunctionResponse();
                    BeanUtils.copyProperties(item, returnItem);
                    returnItem.setHasApiList(apiFunctionList);
                    returnItem.setHasMenu(root);
                    return returnItem;
                })
                .toList();
    }

    /**
     * retrieveMenuHierarchy:
     * 
     * @return
     */
    public FunctionHierarchy retrieveMenuHierarchy() {
        List<FunctionHierarchy> foundFunctionList = functionRepository.findAll().stream()
                .filter(item -> FunctionType.UI == item.getType())
                .map(item -> {
                    FunctionHierarchy returnItem = new FunctionHierarchy();
                    BeanUtils.copyProperties(item, returnItem);
                    return returnItem;
                })
                .collect(Collectors.toList());

        FunctionHierarchy root = new FunctionHierarchy();
        root.setName("null-root");
        root.setFunctionId(null);
        return HierarchyProcessor.transform(root, foundFunctionList);
    }
}
