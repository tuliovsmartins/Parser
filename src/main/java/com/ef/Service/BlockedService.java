package com.ef.Service;

import java.util.Collection;

import com.ef.Entity.BlockedIps;
import com.ef.Interface.BlockedIpsInterface;
import com.ef.Repository.BlockedIpsRepository;
import com.ef.View.Model.ArgumentsVM;
import com.google.inject.Inject;

public class BlockedService {
	
	private final BlockedIpsInterface blockedIpsInterface;
	
	@Inject
    public BlockedService(BlockedIpsRepository blockedIpsRepository) {
        this.blockedIpsInterface = blockedIpsRepository;
    }
	
	public void printLogAnswers(ArgumentsVM queryArguments) {
        System.out.println(String.format(
                "Ips from %s to %s with more than %d requests",
                queryArguments.getStartDate(),
                queryArguments.getEndDate(),
                queryArguments.getThreshold()
        ));
        System.out.println("=========================================");

        final Collection<BlockedIps> result =
        		blockedIpsInterface.findByDateRangeAndThreshold(
                        queryArguments.getStartDate(),
                        queryArguments.getEndDate(),
                        queryArguments.getDuration(),
                        queryArguments.getThreshold()
                );
        
        blockedIpsInterface.save(result);
        
        result.stream().map(blockedIps -> String.format(
        			"-%s: \t%d requests", 
        			blockedIps.getIp(), 
        			blockedIps.getNumberOfHits())
        		).forEach(System.out::println);

        System.out.println("=========================================");
        System.out.printf("  A total of %d ips where found%n", result.size());
        System.out.println("=========================================");
    }

}
