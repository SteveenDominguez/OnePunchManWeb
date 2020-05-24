package OnePunchManWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import OnePunchManWeb.model.Heroe;

import OnePunchManWeb.service.IHeroeService;

@Controller
public class HomeController {
	
	@Autowired
	private IHeroeService hero;
	
	@RequestMapping(value= "/", method = RequestMethod.GET)
	public String HomePage() {
		return "home";
	}
	
	@RequestMapping(value= "/listarHeroes", method = RequestMethod.GET)
	public String ListarHeores(Model modelo) {
		List<Heroe> heroes= hero.listarHeroes();
		modelo.addAttribute("heroes",heroes);
		return "listarHeroes";
	}
	//Controladores para insertar heroe
	@RequestMapping(value = "/insertarHeroes")
	public String InsertarHeroes(Model model) {
		List<Heroe> heroes= hero.listarHeroes();
		model.addAttribute("heroes",heroes);
		return "insertarHeroes";
	}

	@PostMapping(value = "/guardarHeroes")
	public String guardarHeroes(Model modelo, @RequestParam("id") int id, @RequestParam("nombre") 
	String nombre, @RequestParam("rango") String rango, @RequestParam("habilidad") String habilidad, @RequestParam("residencia") String residencia,
	 @RequestParam("telefono") String telefono,  @RequestParam("tiene_celula") String tiene_celula, @RequestParam("fan") int id_fan) {
		System.out.println(id_fan);
		Heroe fan= hero.encontrarPorId(id_fan);
		Heroe heroe= new Heroe(id, nombre, rango, habilidad, residencia, telefono, tiene_celula, fan);
		hero.guardar(heroe);
		List<Heroe> heroes= hero.listarHeroes();
		modelo.addAttribute("heroes",heroes);
		return "listarHeroes";
	}
	//Fin controladores para insertar heroes

	@RequestMapping(value = "/eliminarHeroe/{id}")
	public String eliminarHeroe(@PathVariable("id") int id,Model modelo) {
		hero.eliminar(id);
		List<Heroe>heroes=hero.listarHeroes();
		modelo.addAttribute("heroes",heroes);
		return "redirect:/listarHeroes";
	}

	@RequestMapping(value="/editarHeroe/{id}")
	public String editarHeroe(@PathVariable("id") int id,Model modelo) {
		List<Heroe> heroes= hero.listarHeroes();
		modelo.addAttribute("heroes",heroes);
		Heroe eldato=hero.encontrarPorId(id);
		modelo.addAttribute("heroe",eldato);
		return "editarHeroes";
	}

	@PostMapping(value="/editarHeroe/guardarNuevoHeroe")
	public String guardarNuevoSubdito(Model modelo, @RequestParam("id") int id, @RequestParam("nombre") 
	String nombre, @RequestParam("rango") String rango, @RequestParam("habilidad") String habilidad, @RequestParam("residencia") String residencia,
	 @RequestParam("telefono") String telefono,  @RequestParam("tiene_celula") String tiene_celula, @RequestParam("fan") int id_fan)
	{
		Heroe eldato=hero.encontrarPorId(id);
		eldato.setNombre(nombre);
		eldato.setRango(rango);
		eldato.setHabilidad(habilidad);
		eldato.setResidencia(residencia);
		eldato.setTelefono(telefono);
		eldato.setTiene_celula(tiene_celula);
		Heroe fan= hero.encontrarPorId(id_fan);
		eldato.setFan(fan);

		hero.guardar(eldato);
		List<Heroe> heroes=hero.listarHeroes();
		modelo.addAttribute("heroes",heroes);
		return "redirect:/listarHeroes";
	}
}
