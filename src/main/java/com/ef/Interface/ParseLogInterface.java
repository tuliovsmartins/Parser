package com.ef.Interface;

import java.util.Arrays;
import java.util.Collection;

import com.ef.Entity.ParsedLog;

public interface ParseLogInterface {
	
    void save(Collection<ParsedLog> parsedLog);

    default void save(ParsedLog... parsedLog) {
        save(Arrays.asList(parsedLog));
    }

}
