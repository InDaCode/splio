package com.velib.config;

import com.velib.VelibDataSetFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
@Configuration
public class VelibConfig {
    @Bean
    public VelibDataSetFormatter velibDataSetFormatter(@Value("${velib.dataset.file.path}") String sourceFile){
        return new VelibDataSetFormatter(sourceFile);
    }
}
