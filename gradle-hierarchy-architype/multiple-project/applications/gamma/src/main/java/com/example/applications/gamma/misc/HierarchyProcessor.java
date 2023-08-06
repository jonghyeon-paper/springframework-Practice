package com.example.applications.gamma.misc;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

/**
 * HierarchyProcessor
 * 
 * @author _sCream
 *
 */
@Slf4j
public class HierarchyProcessor {

    /**
     * transform:
     * 
     * @param <T>
     * @param <R>
     * @param root
     * @param targetList
     * @return
     */
    public static <T, R extends Hierarchy<T>> R transform(R root, List<R> targetList) {
        int previousSize = 0;
        do {
            previousSize = targetList.size();
            for (int i = targetList.size() - 1; i > -1; i--) {
                Hierarchy<T> sourceItem = targetList.get(i);
                if (root.addChildIfMatched(sourceItem)) {
                    targetList.remove(i);
                }
            }
        } while (!targetList.isEmpty() && previousSize > targetList.size());
        return root;
    }

    /**
     * Hierarchy:
     * 
     * @author _sCream
     *
     * @param <T>
     */
    public static interface Hierarchy<T> {

        T getIdentifier();
        T getParentIdentifier();
        Integer getPriority();
        List<Hierarchy<T>> getHierarchyChildList();
        boolean hasChild();
        boolean addChildIfMatched(Hierarchy<T> targetData);
        void removeChildIfMatch(Set<T> excludeTargets);
    }

    /**
     * HierarchyAbstract:
     * 
     * @author _sCream
     *
     * @param <T>
     */
    public static abstract class HierarchyAbstract<T> implements Hierarchy<T> {

        /* (non-Javadoc)
         * @see com.prototype.authorization.module.hierarchy.HierarchyProcessor.Hierarchy#hasChild()
         */
        @Override
        public boolean hasChild() {
            return getHierarchyChildList() != null && !getHierarchyChildList().isEmpty();
        }

        /* (non-Javadoc)
         * @see com.prototype.authorization.module.hierarchy.HierarchyProcessor.Hierarchy#addChildIfMatched(com.prototype.authorization.module.hierarchy.HierarchyProcessor.Hierarchy)
         */
        @Override
        public boolean addChildIfMatched(Hierarchy<T> targetData) {
            if (targetData.getParentIdentifier() == null) {
                /*
                 * parent identifier is null.(= first level data)
                 * 
                 * ex:
                 * NullRoot(= dummy data)
                 *  └ {first level}
                 *    └ {second level}
                 *      └ {third level}
                 *      ...
                 */

                // parent-identifier is null
                if (getIdentifier() == null) {
                    // null root
                    if (getHierarchyChildList() == null) {
                        throw new RuntimeException("'HierarchyChildList' is null.");
                    }
                    getHierarchyChildList().add(targetData);
                    getHierarchyChildList().sort(Comparator.comparing(Hierarchy<T>::getPriority));
                    return true;
                } else {
                    // cannot found parent
                    log.error("cannot found parent.");
                    throw new RuntimeException("cannot found parent.");
                }
            } else if (targetData.getParentIdentifier().equals(getIdentifier())) {
                // matched current
                if (getHierarchyChildList() == null) {
                    throw new RuntimeException("'HierarchyChildList' is null.");
                }
                getHierarchyChildList().add(targetData);
                getHierarchyChildList().sort(Comparator.comparing(Hierarchy<T>::getPriority));
                return true;
            } else if (hasChild()) {
                // recursive
                for (Hierarchy<T> childItem : getHierarchyChildList()) {
                    if (childItem.addChildIfMatched(targetData)) {
                        return true;
                    }
                }
            }
            return false;
        }

        /* (non-Javadoc)
         * @see com.prototype.authorization.module.hierarchy.HierarchyProcessor.Hierarchy#removeChildIfMatch(java.util.Set)
         */
        @Override
        public void removeChildIfMatch(Set<T> excludeTargets) {
            if (hasChild()) {
                for (int i = getHierarchyChildList().size() - 1; i > -1; i--) {
                    Hierarchy<T> childItem = getHierarchyChildList().get(i);
                    childItem.removeChildIfMatch(excludeTargets);
                    if (excludeTargets.contains(childItem.getIdentifier()) && !childItem.hasChild()) {
                        getHierarchyChildList().remove(i);
                    }
                }
            }
        }
    }
}
