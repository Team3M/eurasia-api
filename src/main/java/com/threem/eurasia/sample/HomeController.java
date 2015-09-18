package com.threem.eurasia.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	 @RequestMapping(value="/eurasia", method=RequestMethod.GET)
	 public ModelAndView example() {
		 return new ModelAndView("eurasia", "message", "Hello World");
	 }
	
}
