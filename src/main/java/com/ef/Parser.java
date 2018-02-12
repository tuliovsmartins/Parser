package com.ef;

import java.io.FileNotFoundException;
import com.ef.Config.ParseSqlConfig;
import com.ef.Config.ParserConfig;
import com.ef.Controller.ParserController;
import com.ef.Exception.ParserException;
import com.ef.Service.BlockedService;
import com.ef.Service.LogService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;

@Singleton
public class Parser {
		
		
		public static void main(String[] args) throws FileNotFoundException {
			
			try {
            	
			//Inject config classes
			Injector injector = Guice.createInjector(new ParserConfig(), new ParseSqlConfig());

            //Start persistence class instance
            PersistService instance = injector.getInstance(PersistService.class);
            instance.start();
            
            // Inject Service Classes			
			final LogService LogService = injector.getInstance(LogService.class);
			final BlockedService BlockedService = injector.getInstance(BlockedService.class);
			
			//Prepare Argument_statements and call service methods
			ParserController.logFileArg(args).ifPresent(LogService::logFilePersist);
			ParserController.detailsArgs(args).ifPresent(BlockedService::printLogAnswers);
			
			} catch (ParserException ex) {
	        	
	            System.out.println(ex.getMessage());
	            System.exit(1);
	        }
	}
			
}
