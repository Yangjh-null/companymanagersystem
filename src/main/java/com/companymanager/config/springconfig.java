package com.companymanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class springconfig implements WebMvcConfigurer {

    //自定义的转换器  string-----》timestamp
    @Bean
    public Converter<String,Timestamp> timestampConverter(){
        return new Converter<String, Timestamp>() {
            @Override
            public Timestamp convert(String s) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = simpleDateFormat.parse(s);
                    return  new Timestamp(date.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
               return null;
            }
        };
    }
}
