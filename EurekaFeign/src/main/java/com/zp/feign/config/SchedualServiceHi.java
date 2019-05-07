package com.zp.feign.config;

import com.zp.feign.service.SchedualServiceHiService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-client",fallback = SchedualServiceHiService.class)
public interface SchedualServiceHi {
    @RequestMapping(value = "",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
