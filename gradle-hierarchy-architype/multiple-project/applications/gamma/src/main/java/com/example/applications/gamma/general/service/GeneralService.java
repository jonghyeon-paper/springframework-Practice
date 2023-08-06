package com.example.applications.gamma.general.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.applications.gamma.function.model.FunctionHierarchy;
import com.example.applications.gamma.misc.HierarchyProcessor;
import com.example.cores.gamma.jpa.constant.FunctionType;
import com.example.cores.gamma.jpa.entity.AuthoritysFunction;
import com.example.cores.gamma.jpa.entity.Function;
import com.example.cores.gamma.jpa.repository.AuthoritysFunctionRepository;
import com.example.cores.gamma.jpa.repository.FunctionRepository;

import lombok.RequiredArgsConstructor;

/**
 * GeneralService
 * 
 * @author _sCream
 *
 */
@Service
@RequiredArgsConstructor
public class GeneralService {

    private final AuthoritysFunctionRepository authoritysFunctionRepository;
    private final FunctionRepository functionRepository;

    /**
     * retrieveAccessableMenu:
     * 
     * @param authritis
     * @return
     */
    public FunctionHierarchy retrieveAccessableMenu(String... authritis) {
        FunctionHierarchy root = new FunctionHierarchy();
        root.setName("null-root");
        root.setFunctionId(null);

        List<AuthoritysFunction> foundAuthorityFunctionList = authoritysFunctionRepository.findByAuthorityIdIn(Arrays.asList(authritis));
        if (foundAuthorityFunctionList == null || foundAuthorityFunctionList.isEmpty()) {
            return root;
        }

        List<Function> foundFunctionList = functionRepository.findByFunctionIdIn(foundAuthorityFunctionList.stream().map(AuthoritysFunction::getFunctionId).toList());
        if (foundFunctionList == null || foundFunctionList.isEmpty()) {
            return root;
        }

        List<FunctionHierarchy> accessableMenuList = foundFunctionList.stream()
                .filter(item -> FunctionType.UI == item.getType()) // only ui
                .map(item -> {
                    FunctionHierarchy returnItem = new FunctionHierarchy();
                    BeanUtils.copyProperties(item, returnItem);
                    return returnItem;
                })
                .collect(Collectors.toList());

        return HierarchyProcessor.transform(root, accessableMenuList);
    }
}
