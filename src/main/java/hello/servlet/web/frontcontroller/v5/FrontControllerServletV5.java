package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    /**
     * V5 구조 (어댑터 패턴)
     * 1. 핸들러(컨트롤러) 매핑정보 조회 (handlerMappingMap)
     * 2. 핸들러 어댑터 목록 조회 (handlerAdapters)
     * 3. handle(handler)을 호출해서 핸들러 어댑터 호출
     * 4. 핸들러 어댑터가 핸들러 호출
     * 5. 핸들러에서 비즈니스 로직 처리 후 뷰 반환(V3 -> ModelView, V4 -> String)
     * 6. viewResolver() 호출 후 MyView 객체로 변환 후 반환
     * 7. MyView 객체의 render() 를 호출하여 RequestDispatcher 객체를 request 로 부터 가져온 후 포워딩
     * <p>
     * RequestDispatcher : 클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는(보내는)
     * 역할을 수행하거나, 특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 클래스
     */

    // 의존성 분리해보기(DI 적용)

    // private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final HandlerMappingMap handlerMappingMap;
    // private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
    private final HandlerAdapterList handlerAdapterList;


    // public FrontControllerServletV5() {
    //     initHandlerMappingMap();
    //     initHandlerAdapters();
    // }

    // private void initHandlerMappingMap() {
    //     handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
    //     handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
    //     handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    //
    //     handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
    //     handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
    //     handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    // }

    // private void initHandlerAdapters() {
    //     handlerAdapters.add(new ControllerV3HandlerAdapter());
    //     handlerAdapters.add(new ControllerV4HandlerAdapter());
    // }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("text/plain");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("없는 페이지 입니다!!!");
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView mv = adapter.handle(request, response, handler);

        // 반환된 논리 뷰이름 new-form
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        // for (MyHandlerAdapter adapter : handlerAdapters) {
        //     if (adapter.support(handler)) {
        //         return adapter;
        //     }
        // }
        // throw new IllegalArgumentException("can not find handler adapter. handler = " + handler);
        return handlerAdapterList.getHandlerAdapter(handler);
    }

    private Object getHandler(HttpServletRequest request) {
        // String requestURI = request.getRequestURI();
        // handlerMappingMap.get(requestURI);
        return handlerMappingMap.getHandler(request);
    }
}
