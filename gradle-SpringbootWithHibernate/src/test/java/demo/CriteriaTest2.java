package demo;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import demo.customer.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriteriaTest2 {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

//    @Test
    public void criteriaEqualTest() {
        String equalJsonString = "{\"lastName\":\"Bauer\"}";
        retrieveList(equalJsonString).forEach(System.out::println);
    }
    
//    @Test
    public void criteriaStartingWithTest() {
        String startingWithJsonString = "{\"lastName:startingWith\":\"Des\"}";
        retrieveList(startingWithJsonString).forEach(System.out::println);
    }
    
//    @Test
    public void criteriaEndingWithTest() {
        String endingWithJsonString = "{\"lastName:endingWith\":\"er\"}";
        retrieveList(endingWithJsonString).forEach(System.out::println);
    }
    
//    @Test
    public void criteriaContainingTest() {
        String containingJsonString = "{\"lastName:containing\":\"O\"}";
        retrieveList(containingJsonString).forEach(System.out::println);
    }
    
//    @Test
    public void criteriaBetweenTest() {
        String betweenJsonString = "{\"id:between\":[2,3]}";
        retrieveList(betweenJsonString).forEach(System.out::println);
    }
    
//    @Test
    public void criteriaLessThanTest() {
        String lessThanJsonString = "{\"id:lessThan\":3}";
        retrieveList(lessThanJsonString).forEach(System.out::println);
    }
    
    @Test
    public void criteriaInTest() {
        String inJsonString = "{\"id:in\":[1,3,5]}";
//        String inJsonString = "{\"firstName:in\":[\"Jack\",\"David\"]}";
        retrieveList(inJsonString).forEach(System.out::println);
    }
    
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
        return this.findByDynaicCondition(filter);
    }
    
    // store
    private List<Customer> findByDynaicCondition(String filter) {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        List<Customer> customerList = this.executeCriteriaQuery(session, Customer.class, filter);
        session.close();
        return customerList;
    }
    
    // criteria support
    private <T> List<T> executeCriteriaQuery(Session session, Class<T> targetClass, String filter) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = this.generateCriteriaQuery(criteriaBuilder, targetClass, filter);
        Query<T> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
    
    // criteria support
    private <T> CriteriaQuery<T> generateCriteriaQuery(CriteriaBuilder criteriaBuilder, Class<T> targetClass, String filter) {
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(targetClass);
        Root<T> root = criteriaQuery.from(targetClass);
        
        Map<String, Object> filtereMap = this.filterJsonToMap(filter);
        List<Predicate> predicateList = filtereMap.entrySet().stream()
                .map(conditionItem -> {
                    String columnName = this.getColumnName(conditionItem.getKey());
                    String action = this.getAction(conditionItem.getKey());
                    
                    if (EQUAL.equals(action)) {
                        return criteriaBuilder.equal(root.get(columnName), conditionItem.getValue());
                    } else if (STARTING_WITH.equals(action)) {
                        return criteriaBuilder.like(root.get(columnName), conditionItem.getValue() + "%");
                    }  else if (ENDING_WITH.equals(action)) {
                        return criteriaBuilder.like(root.get(columnName), "%" + conditionItem.getValue());
                    } else if (CONTAINING.equals(action)) {
                        return criteriaBuilder.like(root.get(columnName), "%" + conditionItem.getValue() + "%");
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
                            return criteriaBuilder.between(root.get(columnName), fore, rear);
                        } else if (root.get(columnName).getJavaType().equals(BigDecimal.class) ||
                                Number.class.isAssignableFrom(root.get(columnName).getJavaType())) {
                            return criteriaBuilder.between(root.get(columnName), new BigDecimal(fore), new BigDecimal(rear));
                        } else if (root.get(columnName).getJavaType().equals(Timestamp.class)) {
                            return criteriaBuilder.between(root.get(columnName), this.stringToTimestamp(fore), this.stringToTimestamp(rear));
                        }
                    } else if (LESS_THAN.equals(action)) {
                        String value = String.valueOf(conditionItem.getValue());
                        if (root.get(columnName).getJavaType().equals(String.class)) {
                            return criteriaBuilder.lessThan(root.get(columnName), value);
                        } else if (root.get(columnName).getJavaType().equals(BigDecimal.class) ||
                                Number.class.isAssignableFrom(root.get(columnName).getJavaType())) {
                            return criteriaBuilder.lessThan(root.get(columnName), new BigDecimal(value));
                        } else if (root.get(columnName).getJavaType().equals(Timestamp.class)) {
                            return criteriaBuilder.lessThan(root.get(columnName), this.stringToTimestamp(value));
                        }
                    } else if (LESS_THAN_EQUAL.equals(action)) {
                        String value = String.valueOf(conditionItem.getValue());
                        if (root.get(columnName).getJavaType().equals(String.class)) {
                            return criteriaBuilder.lessThanOrEqualTo(root.get(columnName), value);
                        } else if (root.get(columnName).getJavaType().equals(BigDecimal.class) ||
                                Number.class.isAssignableFrom(root.get(columnName).getJavaType())) {
                            return criteriaBuilder.lessThanOrEqualTo(root.get(columnName), new BigDecimal(value));
                        } else if (root.get(columnName).getJavaType().equals(Timestamp.class)) {
                            return criteriaBuilder.lessThanOrEqualTo(root.get(columnName), this.stringToTimestamp(value));
                        }
                    } else if (GREATER_THAN.equals(action)) {
                        String value = String.valueOf(conditionItem.getValue());
                        if (root.get(columnName).getJavaType().equals(String.class)) {
                            return criteriaBuilder.greaterThan(root.get(columnName), value);
                        } else if (root.get(columnName).getJavaType().equals(BigDecimal.class) ||
                                Number.class.isAssignableFrom(root.get(columnName).getJavaType())) {
                            return criteriaBuilder.greaterThan(root.get(columnName), new BigDecimal(value));
                        } else if (root.get(columnName).getJavaType().equals(Timestamp.class)) {
                            return criteriaBuilder.greaterThan(root.get(columnName), this.stringToTimestamp(value));
                        }
                    } else if (GREATER_THAN_EQUAL.equals(action)) {
                        String value = String.valueOf(conditionItem.getValue());
                        if (root.get(columnName).getJavaType().equals(String.class)) {
                            return criteriaBuilder.greaterThanOrEqualTo(root.get(columnName), value);
                        } else if (root.get(columnName).getJavaType().equals(BigDecimal.class) ||
                                Number.class.isAssignableFrom(root.get(columnName).getJavaType())) {
                            return criteriaBuilder.greaterThanOrEqualTo(root.get(columnName), new BigDecimal(value));
                        } else if (root.get(columnName).getJavaType().equals(Timestamp.class)) {
                            return criteriaBuilder.greaterThanOrEqualTo(root.get(columnName), this.stringToTimestamp(value));
                        }
                    } else if (IN.equals(action)) {
                        if (!(conditionItem.getValue() instanceof ArrayList)) {
                            throw new RuntimeException("'" + columnName + "' value invalid.");
                        }
                        if (Number.class.isAssignableFrom(root.get(columnName).getJavaType())) {
                            List<?> valueList2 = ((List<?>) conditionItem.getValue());
                            return root.get(columnName).in(valueList2.stream().map(item2 -> new BigDecimal(String.valueOf(item2))).collect(Collectors.toList()));
                        } else {
                            return root.get(columnName).in(conditionItem.getValue());
                        }
                    }
                    return null;
                })
                .collect(Collectors.toList());
        
        criteriaQuery.select(root).where(predicateList.toArray(new Predicate[predicateList.size()]));
        return criteriaQuery;
    }
    
    private Map<String, Object> filterJsonToMap(String jsonString) {
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        return new Gson().fromJson(jsonString, type);
    }
    
    private String getColumnName(String key) {
        return  key.split(":")[0];
    }
    
    private String getAction(String key) {
        String[] keyNameArray = key.split(":");
        String action = keyNameArray[1];
        if (keyNameArray.length == 1) {
            return EQUAL;
        }
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
