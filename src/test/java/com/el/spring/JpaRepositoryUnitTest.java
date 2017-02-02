package com.el.spring;

import static java.util.Collections.*;
import static org.mockito.Mockito.*;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.el.spring.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.query.JpaEntityGraph;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Unit tests for {@link JpaRepositoryUnitTest}.
 *
 * @author Oliver Gierke
 * @author Thomas Darimont
 * @author Mark Paluch
 */
@RunWith(MockitoJUnitRunner.class)
public class JpaRepositoryUnitTest {

    SimpleJpaRepository<User, Integer> repo;

    @Mock EntityManager em;
    @Mock CriteriaBuilder builder;
    @Mock CriteriaQuery<User> criteriaQuery;
    @Mock CriteriaQuery<Long> countCriteriaQuery;
    @Mock TypedQuery<User> query;
    @Mock TypedQuery<Long> countQuery;
    @Mock
    JpaEntityInformation<User, Long> information;
    @Mock
    CrudMethodMetadata metadata;
    @Mock EntityGraph<User> entityGraph;
    @Mock org.springframework.data.jpa.repository.EntityGraph entityGraphAnnotation;

    public JpaRepositoryUnitTest() {
    }

    @Before
    public void setUp() {

        when(em.getDelegate()).thenReturn(em);

        when(information.getJavaType()).thenReturn(User.class);
        when(em.getCriteriaBuilder()).thenReturn(builder);

        when(builder.createQuery(User.class)).thenReturn(criteriaQuery);
        when(builder.createQuery(Long.class)).thenReturn(countCriteriaQuery);

        when(em.createQuery(criteriaQuery)).thenReturn(query);
        when(em.createQuery(countCriteriaQuery)).thenReturn(countQuery);

        repo = new SimpleJpaRepository<User, Integer>(information, em);
        repo.setRepositoryMethodMetadata(metadata);
    }

    @Test // DATAJPA-124, DATAJPA-912
    public void retrieveObjectsForPageableOutOfRange() {

        when(countQuery.getSingleResult()).thenReturn(20L);
        repo.findAll(new PageRequest(2, 10));

        verify(query).getResultList();
    }

    @Test // DATAJPA-912
    public void doesNotRetrieveCountWithoutOffsetAndResultsWithinPageSize() {

        when(query.getResultList()).thenReturn(Arrays.asList(new User(), new User()));

        repo.findAll(new PageRequest(0, 10));

        verify(countQuery, never()).getSingleResult();
    }

    @Test // DATAJPA-912
    public void doesNotRetrieveCountWithOffsetAndResultsWithinPageSize() {

        when(query.getResultList()).thenReturn(Arrays.asList(new User(), new User()));

        repo.findAll(new PageRequest(2, 10));

        verify(countQuery, never()).getSingleResult();
    }

    @Test(expected = EmptyResultDataAccessException.class) // DATAJPA-177
    public void throwsExceptionIfEntityToDeleteDoesNotExist() {

        repo.delete(4711);
    }

    @Test // DATAJPA-689, DATAJPA-696
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void shouldPropagateConfiguredEntityGraphToFindOne() throws Exception {

        String entityGraphName = "User.detail";
        when(entityGraphAnnotation.value()).thenReturn(entityGraphName);
        when(entityGraphAnnotation.type()).thenReturn(EntityGraphType.LOAD);
        when(metadata.getEntityGraph()).thenReturn((JpaEntityGraph) entityGraphAnnotation);
        //when(em.getEntityGraph(entityGraphName)).thenReturn( entityGraph);
        when(information.getEntityName()).thenReturn("User");
        //when(metadata.getMethod()).thenReturn(CrudRepository.class.getMethod("findOne", Serializable.class));

        Integer id = 0;
        repo.findOne(id);

        verify(em).find(User.class, id, singletonMap(EntityGraphType.LOAD.getKey(), (Object) entityGraph));
    }
}