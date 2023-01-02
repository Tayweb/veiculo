package br.com.veiculo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.veiculo.model.Marca;
import br.com.veiculo.service.MarcaService;
import br.com.veiculo.utils.MensagemErroConstants;

@Controller(value = "marca-viculo")
public class MarcaController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MarcaService marcaVeiculoService;

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

	@GetMapping(value = "/marca-veiculo")
	public ModelAndView getMarcaVeiculo() {
		ModelAndView modelAndView = new ModelAndView("views/marca-veiculo");

		modelAndView.addObject("marcaList", marcaVeiculoService.getMarca());
		modelAndView.addObject("marca", new Marca());
		return modelAndView;
	}

	@PostMapping(value = "/salvar-marca-veiculo")
	public ModelAndView saveMarcaVeiculo(@Valid @ModelAttribute("marca") Marca marcaVeiculo,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("views/marca-veiculo");
			modelAndView.addObject("marcaList", marcaVeiculoService.getMarca());
			modelAndView.addObject("marca", marcaVeiculo);

			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); // vem das anotações do @NotEmpty e outras da entidade
			}
			modelAndView.addObject("msg", msg);
			return modelAndView;
		}

		marcaVeiculoService.save(marcaVeiculo);

		ModelAndView modelAndView = new ModelAndView("views/marca-veiculo");
		modelAndView.addObject("marcaList", marcaVeiculoService.getMarca());
		modelAndView.addObject("marca", new Marca());
		modelAndView.addObject("sucess", MensagemErroConstants.CADASTRADO_COM_SUCESSO);

		return modelAndView;
	}

	@GetMapping(value = "/editar-marca-veiculo")
	public ModelAndView editarMarcaVeiculo(@RequestParam("id") Long id, Marca marcaVeiculo)
			throws NotFoundException {

		ModelAndView modelAndView = new ModelAndView("views/marca-veiculo");
		modelAndView.addObject("marcaList", marcaVeiculoService.getMarca());
		modelAndView.addObject("marca", marcaVeiculoService.findByMarcaId(id));
		return modelAndView;
	}

	@GetMapping(value = "/deletar-marca-veiculo")
	public ModelAndView deletarMarcaVeiculo(@RequestParam("id") Long id) {

		ModelAndView modelAndView = new ModelAndView("views/marca-veiculo");
		marcaVeiculoService.deletarMarca(id);
		modelAndView.addObject("marcaList", marcaVeiculoService.getMarca());
		modelAndView.addObject("sucess", MensagemErroConstants.EXCLUIDO_COM_SUCESSO);
		modelAndView.addObject("marca", new Marca());

		return modelAndView;
	}
	
	@PostMapping(value = "/filtro")
	public ModelAndView filtroPesquisa(@ModelAttribute("filtro") Marca marcaVeiculo) {
		
		ModelAndView modelAndView = new ModelAndView("views/marca-veiculo");
		modelAndView.addObject("filtro", marcaVeiculo);
		modelAndView.addObject("marcaList", marcaVeiculoService.filtroPesquisa(marcaVeiculo));
		modelAndView.addObject("pesquisa", marcaVeiculo);
		return modelAndView;
	}

}
