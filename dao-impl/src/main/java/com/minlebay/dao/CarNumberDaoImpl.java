package com.minlebay.dao;

import com.minlebay.models.CarNumber;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CarNumberDaoImpl implements CarNumberDao {

    @PersistenceContext
    protected EntityManager manager;

    @Override
    public CarNumber getLastCarNumber() {
        List<CarNumber> carNumbers = manager.createQuery(
                "SELECT c FROM CarNumber c order by c.id desc", CarNumber.class)
                    .setMaxResults(1)
                    .getResultList();
        return carNumbers.isEmpty() ? null : carNumbers.get(0);
    }

    @Override
    public CarNumber getCarNumberByRepresentation(String representation) {
        List<CarNumber> carNumbers =  manager.createQuery(
                "SELECT c FROM CarNumber c WHERE c.representation = :representation ", CarNumber.class)
                    .setParameter("representation", representation)
                    .getResultList();
        return carNumbers.isEmpty() ? null : carNumbers.get(0);
    }

    @Override
    public void add(CarNumber newCarNumber) {
        manager.persist(newCarNumber);
    }
}
