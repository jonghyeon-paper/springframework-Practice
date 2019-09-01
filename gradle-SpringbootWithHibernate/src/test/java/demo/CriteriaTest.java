package demo;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class CriteriaTest {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

//    @Test
    public void criteriaEqualTest() {
        String equalJsonString = "{\"lastName\":\"Bauer\"}";
        Map<String, Object> whereClauseMap = convertMap(equalJsonString);
        criteriaService(whereClauseMap).forEach(System.out::println);
    }
    
//    @Test
    public void criteriaStartingWithTest() {
        String startingWithJsonString = "{\"lastName:startingWith\":\"Des\"}";
        Map<String, Object> whereClauseMap = convertMap(startingWithJsonString);
        criteriaService(whereClauseMap).forEach(System.out::println);
    }
    
//    @Test
    public void criteriaEndingWithTest() {
        String endingWithJsonString = "{\"lastName:endingWith\":\"er\"}";
        Map<String, Object> whereClauseMap = convertMap(endingWithJsonString);
        criteriaService(whereClauseMap).forEach(System.out::println);
    }
    
//    @Test
    public void criteriaContainingTest() {
        String containingJsonString = "{\"lastName:containing\":\"O\"}";
        Map<String, Object> whereClauseMap = convertMap(containingJsonString);
        criteriaService(whereClauseMap).forEach(System.out::println);
    }
    
//    @Test
    public void criteriaBetweenTest() {
        String betweenJsonString = "{\"id:between\":[2,3]}";
        Map<String, Object> whereClauseMap = convertMap(betweenJsonString);
        criteriaService(whereClauseMap).forEach(System.out::println);
    }
    
//    @Test
    public void criteriaLessThanTest() {
        String lessThanJsonString = "{\"id:lessThan\":3}";
        Map<String, Object> whereClauseMap = convertMap(lessThanJsonString);
        criteriaService(whereClauseMap).forEach(System.out::println);
    }
    
    @Test
    public void criteriaInTest() {
        String inJsonString = "{\"id:in\":[1,3,5]}";
//        String inJsonString = "{\"firstName:in\":[\"Jack\",\"David\"]}";
        Map<String, Object> whereClauseMap = convertMap(inJsonString);
        criteriaService(whereClauseMap).forEach(System.out::println);
    }
    
    private Map<String, Object> convertMap(String jsonString) {
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        return new Gson().fromJson(jsonString, type);
    }
    
    private List<Customer> criteriaService(Map<String, Object> whereClauseMap) {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customer = criteriaQuery.from(Customer.class);
        
        
        List<Predicate> predicateList = new ArrayList<>();
        whereClauseMap.entrySet().stream()
            .forEach(item -> {
                String key = item.getKey();
                if (key.split(":").length == 1) {
                    predicateList.add(criteriaBuilder.equal(customer.get(key), item.getValue()));
                } else {
                    String columnName = key.split(":")[0];
                    String action = key.split(":")[1];
                    switch (action) {
                    case "startingWith" :
                        predicateList.add(criteriaBuilder.like(customer.get(columnName), item.getValue() + "%"));
                        break;
                    case "endingWith" :
                        predicateList.add(criteriaBuilder.like(customer.get(columnName), "%" + item.getValue()));
                        break;
                    case "containing" :
                        predicateList.add(criteriaBuilder.like(customer.get(columnName), "%" + item.getValue() + "%"));
                        break;
                    case "between" :
                        if (!(item.getValue() instanceof ArrayList)) {
                            throw new RuntimeException("'" + columnName + "' value invalid.");
                        }
                        List<?> valueList = ((List<?>) item.getValue());
                        if (valueList.size() != 2) {
                            throw new RuntimeException("'" + columnName + "' value length invalid.");
                        }
                        if (customer.get(columnName).getJavaType().equals(String.class)) {
                            String fore = String.valueOf(valueList.get(0));
                            String rear = String.valueOf(valueList.get(1));
                            predicateList.add(criteriaBuilder.between(customer.get(columnName), fore, rear));
                        } else if (customer.get(columnName).getJavaType().equals(BigDecimal.class)) {
                            BigDecimal fore = new BigDecimal(String.valueOf(valueList.get(0)));
                            BigDecimal rear = new BigDecimal(String.valueOf(valueList.get(1)));
                            predicateList.add(criteriaBuilder.between(customer.get(columnName), fore, rear));
                        } else if (customer.get(columnName).getJavaType().equals(Timestamp.class)) {
                            Timestamp fore = new Timestamp(System.currentTimeMillis());
                            Timestamp rear = new Timestamp(System.currentTimeMillis());
                            predicateList.add(criteriaBuilder.between(customer.get(columnName), fore, rear));
                        } else if (Number.class.isAssignableFrom(customer.get(columnName).getJavaType())) {
                            BigDecimal fore = new BigDecimal(String.valueOf(valueList.get(0)));
                            BigDecimal rear = new BigDecimal(String.valueOf(valueList.get(1)));
                            predicateList.add(criteriaBuilder.between(customer.get(columnName), fore, rear));
                        }
                        break;
                    case "lessThan" :
                        if (customer.get(columnName).getJavaType().equals(String.class)) {
                            String value = String.valueOf(item.getValue());
                            predicateList.add(criteriaBuilder.lessThan(customer.get(columnName), value));
                        } else if (customer.get(columnName).getJavaType().equals(BigDecimal.class)) {
                            BigDecimal value = new BigDecimal(String.valueOf(item.getValue()));
                            predicateList.add(criteriaBuilder.lessThan(customer.get(columnName), value));
                        } else if (customer.get(columnName).getJavaType().equals(Timestamp.class)) {
                            Timestamp value = new Timestamp(System.currentTimeMillis());
                            predicateList.add(criteriaBuilder.lessThan(customer.get(columnName), value));
                        } else if (Number.class.isAssignableFrom(customer.get(columnName).getJavaType())) {
                            BigDecimal value = new BigDecimal(String.valueOf(item.getValue()));
                            predicateList.add(criteriaBuilder.lessThan(customer.get(columnName), value));
                        }
                        break;
                    case "lessThanEqual" :
                        break;
                    case "greaterThan" :
                        break;
                    case "greaterThanEqual" :
                        break;
                    case "in" :
                        if (!(item.getValue() instanceof ArrayList)) {
                            throw new RuntimeException("'" + columnName + "' value invalid.");
                        }
                        if (Number.class.isAssignableFrom(customer.get(columnName).getJavaType())) {
                            List<?> valueList2 = ((List<?>) item.getValue());
                            predicateList.add(customer.get(columnName).in(valueList2.stream().map(item2 -> new BigDecimal(String.valueOf(item2))).collect(Collectors.toList())));
                        } else {
                            predicateList.add(customer.get(columnName).in(item.getValue()));
                        }
                        break;
                    default :
                        throw new RuntimeException("'" + action + "' action is not supperted.");
                    }
                }
            });
        criteriaQuery.select(customer).where(predicateList.toArray(new Predicate[predicateList.size()]));
        
        Query<Customer> query = session.createQuery(criteriaQuery);
        List<Customer> customerList = query.getResultList();
        session.close();
        return customerList;
    }
}
