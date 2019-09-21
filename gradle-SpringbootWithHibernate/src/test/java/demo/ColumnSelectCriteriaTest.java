package demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import demo.customer.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ColumnSelectCriteriaTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void callService1() {
        String betweenJsonString = "{\"id:between\":[2,3], \"column\":[\"firstName\"]}";
        retrieveList(betweenJsonString).forEach(System.out::println);
    }
    
    @Test
    public void callService2() {
        String betweenJsonString = "{\"id:between\":[2,3], \"column\":[\"firstName\", \"lastName\"]}";
        retrieveList(betweenJsonString).forEach(System.out::println);
    }
    
    @Test
    public void callService3() {
        String betweenJsonString = "{\"id:between\":[2,3], \"column\":[\"id\", \"firstName\", \"lastName\"]}";
        retrieveList(betweenJsonString).forEach(System.out::println);
    }
    
    private static final String COLUMN = "column";
    private static final String ORDER_BY = "orderBy";

    public static final String EQUAL = "equal";
    public static final String STARTING_WITH = "startingWith";
    public static final String ENDING_WITH = "endingWith";
    public static final String CONTAINING = "containing";
    public static final String BETWEEN = "between";
    public static final String LESS_THAN = "lessThan";
    public static final String LESS_THAN_EQUAL = "lessThanEqual";
    public static final String GREATER_THAN = "greaterThan";
    public static final String GREATER_THAN_EQUAL = "greaterThanEqual";
    public static final String IN = "in";

    @SuppressWarnings("serial")
    public static final Set<String> ACTION_SET = new HashSet<String>() {
        {
            add(EQUAL);
            add(STARTING_WITH);
            add(ENDING_WITH);
            add(CONTAINING);
            add(BETWEEN);
            add(LESS_THAN);
            add(LESS_THAN_EQUAL);
            add(GREATER_THAN);
            add(GREATER_THAN_EQUAL);
            add(IN);
        }
    };

    // service
    private List<Customer> retrieveList(String filter) {
        return executeCriteriaQuery(entityManager, Customer.class, filter);
    }

    // service
    private List<Customer> retrieveList(String methodName, Object... values) {
        return executeMethodQuery(entityManager, Customer.class, methodName, values);
    }

    // method query support
    private <T> List<T> executeMethodQuery(EntityManager entityManager, Class<T> targetClass, String methodName,
            Object... values) {
        String[] keyAndActions = methodName.replaceAll("findBy", "").split("And");
        if (keyAndActions.length != values.length) {
            throw new RuntimeException("Method length and value length do not match.");
        }
        Map<String, Object> filtereMap = new HashMap<>();
        for (int i = 0; i < keyAndActions.length; i++) {
            String columnName = keyAndActions[i].substring(0, 1).toLowerCase() + keyAndActions[i].substring(1);
            Iterator<String> actionIterator = ACTION_SET.iterator();
            while (actionIterator.hasNext()) {
                String actionName = actionIterator.next();
                if (columnName.endsWith(actionName)) {
                    columnName = columnName.substring(0, columnName.length() - actionName.length());
                    columnName += ":" + actionName;
                    break;
                }
            }
            filtereMap.put(columnName, values[i]);
        }
        return executeCriteriaQuery(entityManager, targetClass, new GsonBuilder().setPrettyPrinting().create().toJson(filtereMap));
    }

    // criteria support
    private <T> List<T> executeCriteriaQuery(EntityManager entityManager, Class<T> targetClass, String filter) {
        Map<String, Object> filterMap = this.filterJsonToMap(filter);
        if (filterMap.containsKey(COLUMN)) {
            List<String> columnList = ((List<?>) filterMap.get("column")).stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            filterMap.remove("column");
            
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            if (columnList.size() == 1) {
                CriteriaQuery<Object> criteriaQuery = this.generateCriteriaQuery(criteriaBuilder, targetClass, filterMap, Object.class, columnList);
                TypedQuery<Object> typedQuery = entityManager.createQuery(criteriaQuery);
                List<Object> objectList = typedQuery.getResultList();
                
                List<T> resultList = new ArrayList<>();
                for (Object object : objectList) {
                    String setterMathodName = null;
                    try {
                        T newInstance = targetClass.newInstance();
                        String columnName = columnList.get(0);
                        setterMathodName = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                        Method setterMathod = targetClass.getDeclaredMethod(setterMathodName, object.getClass());
                        setterMathod.invoke(newInstance, object);
                        resultList.add(newInstance);
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException("New instance generate fail.", e);
                    } catch (NoSuchMethodException | SecurityException e) {
                        throw new RuntimeException("'" + setterMathodName + "' method not found.", e);
                    } catch (IllegalArgumentException | InvocationTargetException e) {
                        throw new RuntimeException("'" + setterMathodName + "' method run fail.", e);
                    }
                }
                return resultList;
            } else {
                CriteriaQuery<Object[]> criteriaQuery = this.generateCriteriaQuery(criteriaBuilder, targetClass, filterMap, Object[].class, columnList);
                TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
                List<Object[]> objectList = typedQuery.getResultList();
                
                List<T> resultList = new ArrayList<>();
                for (Object[] object : objectList) {
                    String setterMathodName = null;
                    try {
                        T newInstance = targetClass.newInstance();
                        for (int i = 0; i < columnList.size(); i++) {
                            String columnName = columnList.get(i);
                            setterMathodName = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                            Method setterMathod = targetClass.getDeclaredMethod(setterMathodName, object[i].getClass());
                            setterMathod.invoke(newInstance, object[i]);
                        }
                        resultList.add(newInstance);
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException("New instance generate fail.", e);
                    } catch (NoSuchMethodException | SecurityException e) {
                        throw new RuntimeException("'" + setterMathodName + "' method not found.", e);
                    } catch (IllegalArgumentException | InvocationTargetException e) {
                        throw new RuntimeException("'" + setterMathodName + "' method run fail.", e);
                    }
                }
                return resultList;
            }
        } else {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = this.generateCriteriaQuery(criteriaBuilder, targetClass, filterMap);
            TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
            List<T> resultList = typedQuery.getResultList();
            return resultList;
        }
    }

    // criteria support
    private <T> CriteriaQuery<T> generateCriteriaQuery(CriteriaBuilder criteriaBuilder, Class<T> targetClass, Map<String, Object> filterMap) {
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(targetClass);
        Root<T> root = criteriaQuery.from(targetClass);
        List<Predicate> predicateList = this.generateWhereClause(criteriaBuilder, root, filterMap);
        criteriaQuery.select(root).where(predicateList.toArray(new Predicate[predicateList.size()]));
        return criteriaQuery;
    }
    
    // criteria support
    private <T, U> CriteriaQuery<U> generateCriteriaQuery(CriteriaBuilder criteriaBuilder, Class<T> targetClass, Map<String, Object> filterMap, Class<U> queryType, List<String> columnList) {
        CriteriaQuery<U> criteriaQuery = criteriaBuilder.createQuery(queryType);
        Root<T> root = criteriaQuery.from(targetClass);
        List<Selection<?>> selectionList = columnList.stream()
                .map(item ->  root.get(item))
                .collect(Collectors.toList());
        List<Predicate> predicateList = this.generateWhereClause(criteriaBuilder, root, filterMap);
        criteriaQuery.multiselect(selectionList).where(predicateList.toArray(new Predicate[predicateList.size()]));
        return criteriaQuery;
    }
    
    private <T> List<Predicate> generateWhereClause(CriteriaBuilder criteriaBuilder, Root<T> root, Map<String, Object> filterMap) {
        List<Predicate> predicateList = new ArrayList<>();
        for (Map.Entry<String, Object> conditionItem : filterMap.entrySet()) {
            String columnName = this.getColumnName(conditionItem.getKey());
            String action = this.getAction(conditionItem.getKey());

            Predicate newPredicate = null;
            if (EQUAL.equals(action)) {
                newPredicate = criteriaBuilder.equal(root.get(columnName), conditionItem.getValue());
            } else if (STARTING_WITH.equals(action)) {
                newPredicate = criteriaBuilder.like(root.get(columnName), conditionItem.getValue() + "%");
            } else if (ENDING_WITH.equals(action)) {
                newPredicate = criteriaBuilder.like(root.get(columnName), "%" + conditionItem.getValue());
            } else if (CONTAINING.equals(action)) {
                newPredicate = criteriaBuilder.like(root.get(columnName), "%" + conditionItem.getValue() + "%");
            } else if (BETWEEN.equals(action)) {
                if (!(conditionItem.getValue() instanceof ArrayList)) {
                    throw new RuntimeException("'" + columnName + "' value invalid.");
                }
                List<?> valueList = ((List<?>) conditionItem.getValue());
                if (valueList.size() != 2) {
                    throw new RuntimeException("'" + columnName + "' value length invalid.");
                }

                String fore = String.valueOf(valueList.get(0));
                String rear = String.valueOf(valueList.get(1));
                if (root.get(columnName).getJavaType().equals(String.class)) {
                    newPredicate = criteriaBuilder.between(root.get(columnName), fore, rear);
                } else if (root.get(columnName).getJavaType().equals(BigDecimal.class)
                        || Number.class.isAssignableFrom(root.get(columnName).getJavaType())) {
                    newPredicate = criteriaBuilder.between(root.get(columnName), new BigDecimal(fore),
                            new BigDecimal(rear));
                } else if (root.get(columnName).getJavaType().equals(Timestamp.class)) {
                    newPredicate = criteriaBuilder.between(root.get(columnName), this.stringToTimestamp(fore),
                            this.stringToTimestamp(rear));
                }
            } else if (LESS_THAN.equals(action)) {
                String value = String.valueOf(conditionItem.getValue());
                if (root.get(columnName).getJavaType().equals(String.class)) {
                    newPredicate = criteriaBuilder.lessThan(root.get(columnName), value);
                } else if (root.get(columnName).getJavaType().equals(BigDecimal.class)
                        || Number.class.isAssignableFrom(root.get(columnName).getJavaType())) {
                    newPredicate = criteriaBuilder.lessThan(root.get(columnName), new BigDecimal(value));
                } else if (root.get(columnName).getJavaType().equals(Timestamp.class)) {
                    newPredicate = criteriaBuilder.lessThan(root.get(columnName), this.stringToTimestamp(value));
                }
            } else if (LESS_THAN_EQUAL.equals(action)) {
                String value = String.valueOf(conditionItem.getValue());
                if (root.get(columnName).getJavaType().equals(String.class)) {
                    newPredicate = criteriaBuilder.lessThanOrEqualTo(root.get(columnName), value);
                } else if (root.get(columnName).getJavaType().equals(BigDecimal.class)
                        || Number.class.isAssignableFrom(root.get(columnName).getJavaType())) {
                    newPredicate = criteriaBuilder.lessThanOrEqualTo(root.get(columnName), new BigDecimal(value));
                } else if (root.get(columnName).getJavaType().equals(Timestamp.class)) {
                    newPredicate = criteriaBuilder.lessThanOrEqualTo(root.get(columnName),
                            this.stringToTimestamp(value));
                }
            } else if (GREATER_THAN.equals(action)) {
                String value = String.valueOf(conditionItem.getValue());
                if (root.get(columnName).getJavaType().equals(String.class)) {
                    newPredicate = criteriaBuilder.greaterThan(root.get(columnName), value);
                } else if (root.get(columnName).getJavaType().equals(BigDecimal.class)
                        || Number.class.isAssignableFrom(root.get(columnName).getJavaType())) {
                    newPredicate = criteriaBuilder.greaterThan(root.get(columnName), new BigDecimal(value));
                } else if (root.get(columnName).getJavaType().equals(Timestamp.class)) {
                    newPredicate = criteriaBuilder.greaterThan(root.get(columnName), this.stringToTimestamp(value));
                }
            } else if (GREATER_THAN_EQUAL.equals(action)) {
                String value = String.valueOf(conditionItem.getValue());
                if (root.get(columnName).getJavaType().equals(String.class)) {
                    newPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get(columnName), value);
                } else if (root.get(columnName).getJavaType().equals(BigDecimal.class)
                        || Number.class.isAssignableFrom(root.get(columnName).getJavaType())) {
                    newPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get(columnName), new BigDecimal(value));
                } else if (root.get(columnName).getJavaType().equals(Timestamp.class)) {
                    newPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get(columnName),
                            this.stringToTimestamp(value));
                }
            } else if (IN.equals(action)) {
                if (!(conditionItem.getValue() instanceof ArrayList)) {
                    throw new RuntimeException("'" + columnName + "' value invalid.");
                }
                if (Number.class.isAssignableFrom(root.get(columnName).getJavaType())) {
                    List<?> valueList2 = ((List<?>) conditionItem.getValue());
                    newPredicate = root.get(columnName).in(valueList2.stream()
                            .map(item2 -> new BigDecimal(String.valueOf(item2)))
                            .collect(Collectors.toList()));
                } else {
                    newPredicate = root.get(columnName).in(conditionItem.getValue());
                }
            }
            if (newPredicate != null) {
                predicateList.add(newPredicate);
            }
        }
        return predicateList;
    }

    private Map<String, Object> filterJsonToMap(String jsonString) {
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        return new Gson().fromJson(jsonString, type);
    }

    private String getColumnName(String key) {
        return key.split(":")[0];
    }

    private String getAction(String key) {
        String[] keyNameArray = key.split(":");
        if (keyNameArray.length == 1) {
            return EQUAL;
        }
        String action = keyNameArray[1];
        if (!ACTION_SET.contains(action)) {
            throw new RuntimeException("'" + action + "' is not support.");
        }
        return action;
    }

    private Timestamp stringToTimestamp(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return new Timestamp(sdf.parse(value).getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Date-string invalid.");
        }
    }
}
