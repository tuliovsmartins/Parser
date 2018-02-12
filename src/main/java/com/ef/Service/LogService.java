package com.ef.Service;

import java.io.File;
import java.util.List;

import com.ef.Entity.ParsedLog;
import com.ef.Interface.ParseLogInterface;
import com.ef.Repository.ParseRepository;
import com.google.inject.Inject;

public class LogService {
	
	private final ParseLogInterface parseLogInterface;
	
	
	@Inject
    public LogService(ParseRepository parseRepository) {
        this.parseLogInterface = parseRepository;
    }
	
		
	public void logFilePersist(File file) {
        final List<ParsedLog> requestLogs = new LogParser(file)
                .parseRequestLogs();

        parseLogInterface.save(requestLogs);
    }

    

}
