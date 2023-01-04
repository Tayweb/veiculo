package br.com.veiculo.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController implements Serializable{

	private static final long serialVersionUID = 1L;

	@GetMapping(value = "/")
	public ModelAndView init() {
		ModelAndView modelAndView = new ModelAndView("views/home");
		return modelAndView;
	}

	@GetMapping(value = "/home")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("views/home");
		return modelAndView;
	}
}
