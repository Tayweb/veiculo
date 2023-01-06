package br.com.veiculo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.veiculo.model.Item;
import br.com.veiculo.model.Veiculo;
import br.com.veiculo.service.ItemService;
import br.com.veiculo.service.MarcaService;
import br.com.veiculo.service.VeiculoService;
import br.com.veiculo.utils.MensagemErroConstants;

@Controller
public class VeiculoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private VeiculoService veiculoService;

	@Autowired
	private MarcaService marcaService;

	@Autowired
	private ItemService itemService;

	@GetMapping(value = "/veiculo")
	public ModelAndView getMarcaVeiculo() {
		ModelAndView modelAndView = new ModelAndView("views/veiculo");
		modelAndView.addObject("veiculoList", veiculoService.getVeiculo());
		modelAndView.addObject("marcaList", marcaService.getMarcaAtivas());
		modelAndView.addObject("itemList", itemService.getItem());

		modelAndView.addObject("veiculo", new Veiculo());
		return modelAndView;
	}

	@PostMapping(value = "/salvar")
	public ModelAndView saveVeiculo(@ModelAttribute("veiculo") Veiculo veiculo) throws Exception {

		veiculoService.salvar(veiculo);

		ModelAndView modelAndView = new ModelAndView("views/veiculo");
		
		modelAndView.addObject("marcaList", marcaService.getMarcaAtivas());
		modelAndView.addObject("veiculoList", veiculoService.getVeiculo());
		modelAndView.addObject("itemList", itemService.getItem());
		modelAndView.addObject("veiculo", new Veiculo());
		modelAndView.addObject("sucess", MensagemErroConstants.CADASTRADO_COM_SUCESSO);

		return modelAndView;
	}

}
