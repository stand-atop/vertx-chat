package com.spring.vertx.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("main.do")
	public ModelAndView main(Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		return mav;
	}

	@RequestMapping("main2.do")
	public ModelAndView main2(Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main2");
		return mav;
	}

}