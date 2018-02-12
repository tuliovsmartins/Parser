package com.ef.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.ef.Entity.BlockedIps;
import com.ef.Entity.ParsedLog;
import com.ef.Exception.ParserException;
import com.ef.Interface.BlockedIpsInterface;
import com.ef.Util.Duration;

public class BlockedIpsRepository implements BlockedIpsInterface {

	
	private EntityManager entityManager;

    @Inject
    public BlockedIpsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public void save(Collection<BlockedIps> blockedIps) {
        final EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            blockedIps.forEach(this::insert);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();

            throw new ParserException("Error inserting new blocked ips");
        }
    }
    
    public void insert(BlockedIps blockedIps) {
        Query query = entityManager.createNativeQuery(
                "INSERT INTO blockedips (ip, numberofhits, message) VALUES(?,?,?)"
        );
        
        query.setParameter(1, blockedIps.getIp());
        query.setParameter(2, blockedIps.getNumberOfHits());
        query.setParameter(3, blockedIps.getMessage());

        query.executeUpdate();
    }
    
	@Override
    public Collection<BlockedIps> findByDateRangeAndThreshold(
            Date startDate, Date endDate, Duration duration, Integer threshold
    ) {
		
		if(endDate == null) {
			
			endDate = duration.addTo(startDate);
		}
		
        StringBuilder sb = new StringBuilder("SELECT DISTINCT ip, COUNT(id) as count ");
        sb.append("FROM ParsedLog WHERE ");
        sb.append("date BETWEEN ? and ? GROUP BY ip ");
        sb.append("HAVING COUNT(ip) >= ?");
        
        final Query query = entityManager.createNativeQuery(sb.toString(), ParsedLog.class);
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);
        query.setParameter(3, threshold);
        
        @SuppressWarnings("unchecked")
		final List<BlockedIps> resultList = query.getResultList();
        return resultList;
    }
}
