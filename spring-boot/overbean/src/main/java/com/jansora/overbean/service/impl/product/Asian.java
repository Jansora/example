package com.jansora.overbean.service.impl.product;

import com.jansora.overbean.service.AsianService;
import org.springframework.stereotype.Service;

/**
 * 亚洲
 */
@Service
public class Asian implements AsianService {

    @Override
    public void sayHello() {
        System.out.println("Asian: sayHello.");
    }
}
