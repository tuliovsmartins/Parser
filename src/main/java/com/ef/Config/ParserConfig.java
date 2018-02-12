package com.ef.Config;

import com.ef.Service.LogService;
import com.google.inject.AbstractModule;

public class ParserConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(LogService.class);
    }

}
