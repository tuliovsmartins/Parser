package com.ef.Interface;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import com.ef.Entity.BlockedIps;
import com.ef.Util.Duration;

public interface BlockedIpsInterface {
	
	Collection<BlockedIps> findByDateRangeAndThreshold(
            Date startDate, Date endDate, Duration duration, Integer threshold
    );
	
	void save(Collection<BlockedIps> blockedIps);

    default void save(BlockedIps... blockedIps) {
        save(Arrays.asList(blockedIps));
    }

}
