package hello.servlet.web.frontcontroller.v5.config;

import hello.servlet.web.frontcontroller.v5.HandlerAdapterList;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV2HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class HandlerAdapterConfig {
    @Bean
    public HandlerAdapterList handlerAdapterList(){
        return new HandlerAdapterList(registerHandlerAdapter());
    }

    private List<MyHandlerAdapter> registerHandlerAdapter() {
        List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
        handlerAdapters.add(new ControllerV4HandlerAdapter());
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV2HandlerAdapter());
        return handlerAdapters;
    }
}
