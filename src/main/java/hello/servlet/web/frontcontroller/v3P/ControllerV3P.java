package hello.servlet.web.frontcontroller.v3P;

import hello.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface ControllerV3P {
    ModelView process(Map<String, String> paramMap);
}
