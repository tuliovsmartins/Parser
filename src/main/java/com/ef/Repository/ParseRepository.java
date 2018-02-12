package com.ef.Repository;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.ef.Exception.ParserException;
import com.ef.Interface.ParseLogInterface;
import com.ef.Entity.ParsedLog;

public class ParseRepository implements ParseLogInterface {

    private EntityManager entityManager;

    @Inject
    public ParseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Collection<ParsedLog> requestLogs) {
        final EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            requestLogs.forEach(this::insert);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();

            throw new ParserException("Error while inserting the new request logs");
        }
    }

    public void insert(ParsedLog requestLog) {
        Query query = entityManager.createNativeQuery(
                "INSERT INTO parsedlog (ip, date, requestsource) VALUES(?,?,?)"
        );

        query.setParameter(1, requestLog.getIp());
        query.setParameter(2, requestLog.getDate());
        query.setParameter(3, requestLog.getRequestsource());

        query.executeUpdate();
    }

}
