package com.minlebay.dao;

import com.minlebay.models.CarNumber;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CarNumberDaoImpl implements CarNumberDao {

    @PersistenceContext
    protected EntityManager manager;

    @Override
    public List<CarNumber> getAllCarNumbers() {
        return manager.createQuery("SELECT c FROM CarNumber c order by c.id desc", CarNumber.class)
                .getResultList();
    }

    @Override
    public CarNumber getLastCarNumber() {
        try {
            return manager.createQuery("SELECT c FROM CarNumber c order by c.id desc", CarNumber.class)
                    .setMaxResults(1)
                    .getResultList().get(0);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public CarNumber getCarNumberByRepresentation(String representation) {
        try {
            return manager.createQuery("SELECT c FROM CarNumber c WHERE c.representation = :representation ", CarNumber.class)
                    .setParameter("representation", representation)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void add(CarNumber newCarNumber) {
        manager.persist(newCarNumber);
    }
}
