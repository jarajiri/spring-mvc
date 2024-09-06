package hello.servlet.web.frontcontroller.v3P;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3P.controller.MemberFormControllerV3P;
import hello.servlet.web.frontcontroller.v3P.controller.MemberListControllerV3P;
import hello.servlet.web.frontcontroller.v3P.controller.MemberSaveControllerV3P;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3P", urlPatterns = "/front-controller/v3p/*")
public class FrontControllerServletV3P extends HttpServlet {
    private Map<String, ControllerV3P> controllerMap = new HashMap<>();

    public FrontControllerServletV3P() {
        controllerMap.put("/front-controller/v3p/members/new-form", new MemberFormControllerV3P());
        controllerMap.put("/front-controller/v3p/members/save", new MemberSaveControllerV3P());
        controllerMap.put("/front-controller/v3p/members", new MemberListControllerV3P());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3P controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("text/plain");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("잘못들어왔다.. 집에가라");
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
            .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
