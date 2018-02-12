package com.ef.Controller;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import com.ef.Exception.ParserException;
import com.ef.Util.DateTimeUtil;
import com.ef.Util.Duration;
import com.ef.Util.Ipv4Util;
import com.ef.View.Model.ArgumentsVM;

public class ParserController {

	
	public static Optional<File> logFileArg(String... args) {
	       
		Map<String, String> paramsMap = Ipv4Util.parseArgsIntoMap(args);

        if (!paramsMap.containsKey("accesslog")) {
            return Optional.empty();
        }
        	
        return Optional.of(paramsMap.get("accesslog"))
                .map(File::new).filter(File::canRead)
                .map(Optional::of).orElseThrow(() -> new ParserException("Unable to read file" + paramsMap.get("accesslog").toString()));
    }
	
	public static Optional<ArgumentsVM> detailsArgs(String... args) {
        Map<String, String> paramsMap = Ipv4Util.parseArgsIntoMap(args);

        if (!(paramsMap.containsKey("startDate") || paramsMap.containsKey("duration") || paramsMap.containsKey("threshold"))) {
            return Optional.empty();
        }

        final Date startDate = Optional.of(paramsMap.getOrDefault("startDate", ""))
                .filter(text -> !text.isEmpty())
                .flatMap(DateTimeUtil::parseRequestLogDate)
                .orElseThrow(() -> new ParserException("The startDate is missing or invalid. Usage ex: --startDate  2018-01-01.00:00:00"));
        

        final Duration duration = Optional.of(paramsMap.getOrDefault("duration", ""))
                .filter(text -> !text.isEmpty())
                .flatMap(Duration::parseFromParamName)
                .orElseThrow(() -> new ParserException("The argument duration is missing or invalid. Usage ex: --duration=hourly "));

        final Integer threshold = Optional.of(paramsMap.getOrDefault("threshold", ""))
                .filter(text -> !text.isEmpty())
                .filter(text -> text.matches("\\d+"))
                .map(Integer::parseInt)
                .filter(val -> val > 0)
                .orElseThrow(() -> new ParserException("The argument threshold is missing or invalid. Usage ex: --threshold=5 where threshold bigger than 0"));
        
       
        Date endDate = duration.addTo(startDate);
        
        return Optional.of(new ArgumentsVM(startDate, duration, threshold, endDate));
    }
	
	
}
