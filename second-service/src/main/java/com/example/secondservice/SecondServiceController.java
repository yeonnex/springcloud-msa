package com.example.secondservice;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/second-service")
public class SecondServiceController {
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome to second service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header){
        log.info(header);
        return "Hello world in second-service!";
    }
    @GetMapping("/check")
    public String check(){
        return "Hi, there. This is a message from second-service";
    }


}
