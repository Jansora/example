package com.jansora.overbean.demo;

import com.jansora.overbean.service.AsianService;
import com.jansora.overbean.service.EuropeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Demo {

    @Autowired
    AsianService asianService;

    @Autowired
    EuropeanService europeanService;



}
