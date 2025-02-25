package br.com.nba.api.repositories.impl;

import br.com.nba.api.repositories.PersistenciaDawException;
import br.com.nba.api.repositories.interfaces.RepositoryBase;
import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class RepositoryBaseImpl<E,T> implements RepositoryBase<E,T> {

    @PersistenceContext
    protected EntityManager emf;

    private Class<E> entityClass;

	public RepositoryBaseImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
    return emf;
    }

    @Override
    @Transactional
    public E save(E obj) throws PersistenciaDawException {
        try(EntityManager em = getEntityManager()) {
                em.persist(obj);
                return obj;
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            throw new PersistenciaDawException("Ocorreu algum erro ao tentar salvar a entidade.", pe);
        }
    }

    @Override
    @Transactional
    public E update(E obj) throws PersistenciaDawException {
        try(EntityManager em = getEntityManager()) {
                E resultado = em.merge(obj);
                return resultado;
        } catch (PersistenceException pe) {
                pe.printStackTrace();
                throw new PersistenciaDawException("Ocorreu algum erro ao tentar atualizar a entidade.", pe);
        }
    }

    @Override
    @Transactional
    public void delete(T primaryKey) throws PersistenciaDawException {
        try(EntityManager em = getEntityManager()) {
            E obj = em.getReference(this.entityClass, primaryKey);
            em.remove(obj);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            throw new PersistenciaDawException("Ocorreu algum erro ao tentar remover a entidade.", pe);
        }
    }


    @Override
    public E getByID(T primaryKey) throws PersistenciaDawException {
        try(EntityManager em = getEntityManager()) {
            try {
                return em.find(this.entityClass, primaryKey);
            } catch (PersistenceException pe) {
                pe.printStackTrace();
                throw new PersistenciaDawException("Ocorreu algum erro ao tentar recuperar a entidade com base no ID.", pe);
            }
        }
    }

    @Override
    public List<E> getAll() throws PersistenciaDawException {
        try(EntityManager em = getEntityManager()) {
            try {
                TypedQuery<E> query = em.createQuery(String.format("SELECT obj FROM %s obj", getEntityName()), this.entityClass);
                return query.getResultList();
            } catch (PersistenceException pe) {
                pe.printStackTrace();
                throw new PersistenciaDawException("Ocorreu algum erro ao tentar recuperar todas as instâncias da entidade.", pe);
            }
        }
    }

    protected String getEntityName() {
    return this.entityClass.getSimpleName();
}
}
