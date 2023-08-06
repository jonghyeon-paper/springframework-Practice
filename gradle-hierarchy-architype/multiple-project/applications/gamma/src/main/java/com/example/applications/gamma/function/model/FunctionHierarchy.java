package com.example.applications.gamma.function.model;

import java.util.ArrayList;
import java.util.List;

import com.example.applications.gamma.misc.HierarchyProcessor.Hierarchy;
import com.example.applications.gamma.misc.HierarchyProcessor.HierarchyAbstract;
import com.example.cores.gamma.jpa.constant.FunctionType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * FunctionHierarchy
 * 
 * @author _sCream
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FunctionHierarchy extends HierarchyAbstract<Integer> {

    private Integer functionId;
    private Integer parentFunctionId;
    private String name;
    private FunctionType type;
    private String path;
    private Boolean enabled;
    private List<Hierarchy<Integer>> childList;


    /* (non-Javadoc)
     * @see com.prototype.authorization.module.hierarchy.HierarchyProcessor.Hierarchy#getIdentifier()
     */
    @Override
    public Integer getIdentifier() {
        return functionId;
    }

    /* (non-Javadoc)
     * @see com.prototype.authorization.module.hierarchy.HierarchyProcessor.Hierarchy#getParentIdentifier()
     */
    @Override
    public Integer getParentIdentifier() {
        return parentFunctionId;
    }

    /* (non-Javadoc)
     * @see com.prototype.authorization.module.hierarchy.HierarchyProcessor.Hierarchy#getPriority()
     */
    @Override
    public Integer getPriority() {
        return 0;
    }

    /* (non-Javadoc)
     * @see com.prototype.authorization.module.hierarchy.HierarchyProcessor.Hierarchy#getHierarchyChildList()
     */
    public List<Hierarchy<Integer>> getHierarchyChildList() {
        if (childList == null) {
            childList = new ArrayList<>();
        }
        return childList;
    }
}
