package br.com.nba.api.services.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericSpecification<T> implements Specification<T> {

    private final Map<String, Object> filters;

    public GenericSpecification(Map<String, Object> filters) {
        this.filters = filters;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value != null) {
                if (value instanceof String) {
                    predicates.add(cb.like(cb.lower(root.get(key)), "%" + value.toString().toLowerCase() + "%"));
                } else {
                    predicates.add(cb.equal(root.get(key), value));
                }
            }
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}