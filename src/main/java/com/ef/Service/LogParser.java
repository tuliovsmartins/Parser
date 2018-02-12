package com.ef.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.ef.Entity.ParsedLog;
import com.ef.Exception.ParserException;
import com.ef.Util.DateTimeUtil;
import com.ef.Util.Ipv4Util;

public final class LogParser {
	
		private final Scanner scanner;

	    public LogParser(String text){
	        this.scanner = new Scanner(text);
	    }

	    public LogParser(File logFile){
	        try {
	            scanner = new Scanner(logFile, "UTF-8");
	        }catch (FileNotFoundException ex){
	            throw new ParserException(String.format(
	                    "The log file '%s' was not found",
	                    logFile
	            ));
	        }
	    }

	    public final List<ParsedLog> parseRequestLogs() {
	        scanner.useDelimiter("\\s*\\|\\s*");

	        List<ParsedLog> requestLogs = new LinkedList<>();

	        while(scanner.hasNext())
	        {
	            parseRequestLog(scanner.nextLine())
	                    .ifPresent(requestLogs::add);
	        }

	        return requestLogs;
	    }
	    
	    protected Optional<ParsedLog> parseRequestLog(String line) {
	        final String[] tokens = line.split("\\s*\\|\\s*");
	        
	        String requestsource = parseRequestSource(tokens);
	        
	        final Optional<ParsedLog> requestLog = parseIp(tokens)
	                .flatMap(ip -> parseDate(tokens).map(
	                        date -> new ParsedLog(
	                                ip, date, requestsource
	                        )
	                ));

	        return requestLog;
	    }

	    private Optional<String> parseIp(final String[] tokens) {
	        return Ipv4Util.parseIpv4Address(tokens[1]);
	    }

	    private Optional<Date> parseDate(String[] tokens) {
	    	String[] formated = tokens[0].split("\\s*\\.\\s*");
	        return DateTimeUtil.parseRequestLogDate(formated[0].replace(" ", "."));
	    }
	    
	    private String parseRequestSource(String[] tokens) {
	        return tokens[4];
	    }

}
