package hello.servlet.web.frontcontroller.v3P.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3P.ControllerV3P;

import java.util.Map;

public class MemberFormControllerV3P implements ControllerV3P {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
