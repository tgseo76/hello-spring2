package com.example.hello2.controller;

import com.example.hello2.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
@Slf4j

public class GetController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String getHello()
    {log.info("hello로 오청");
        return "Hello world";
    }

    @GetMapping(value = "/name")
    public String getName()
    {
        log.info("name로 오청");
        return "taegeon";
    }

    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        log.info("val로 오청");
        return variable;
    }

    @GetMapping(value = "/request1")
    public String getRequestParam1(@RequestParam String name, @RequestParam String email, @RequestParam String organization){
        return name+" "+email+" "+organization;
    }

    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String,String> param){
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey()+":"+map.getValue()+"\n");
        });
        return sb.toString();
    }

    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDto){
//        return memberDto.getName()+ "" +memberDto.getEmail()+""+memberDto.getOrganization();
        return memberDto.toString();
    }



}
