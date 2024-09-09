package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ControllerV2HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV2);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV2 controller = (ControllerV2) handler;
        MyView myView = controller.process(request, response);
        return getModelView(myView);
    }

    private ModelView getModelView(MyView myView) {
        String viewName = myView.getViewPath().replace("/WEB-INF/views/", "").replace(".jsp", "");
        return new ModelView(viewName);
    }
}
