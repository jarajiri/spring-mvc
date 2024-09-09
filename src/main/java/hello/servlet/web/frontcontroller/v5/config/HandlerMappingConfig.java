package hello.servlet.web.frontcontroller.v5.config;

import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.HandlerMappingMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HandlerMappingConfig {

    @Bean
    public HandlerMappingMap handlerMappingMap(){
        return new HandlerMappingMap(registerHandlers());
    }

    private Map<String, Object> registerHandlers() {
        Map<String,Object> urlMap = new HashMap<>();
        //V2
        urlMap.put("/front-controller/v5/v2/members/new-form", new MemberFormControllerV2());
        urlMap.put("/front-controller/v5/v2/members/save", new MemberSaveControllerV2());
        urlMap.put("/front-controller/v5/v2/members", new MemberListControllerV2());
        //V3
        urlMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        urlMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        urlMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        //V4
        urlMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        urlMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        urlMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
        return urlMap;
    }

}
