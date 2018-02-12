package com.ef.Config;

import com.ef.Interface.ParseLogInterface;
import com.ef.Repository.ParseRepository;
import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

public class ParseSqlConfig extends AbstractModule {

    @Override
    protected void configure() {
        install(new JpaPersistModule("parseconfig"));
        bind(ParseLogInterface.class).to(ParseRepository.class);
    }
    

}
