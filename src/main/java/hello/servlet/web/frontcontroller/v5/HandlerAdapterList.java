package hello.servlet.web.frontcontroller.v5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

public class HandlerAdapterList {
    private final List<MyHandlerAdapter> handlerAdapters;

    public HandlerAdapterList(List<MyHandlerAdapter> handlerAdapters) {
        this.handlerAdapters = handlerAdapters;
    }
    public MyHandlerAdapter getHandlerAdapter(Object handler) {
        for(MyHandlerAdapter ha : handlerAdapters){
            if(ha.support(handler)){
                return ha;
            }
        }
        throw new IllegalArgumentException("can't find handler adapter. handler = " + handler);
    }
}
