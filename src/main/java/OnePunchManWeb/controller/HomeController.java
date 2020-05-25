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

import OnePunchManWeb.model.Comida;
import OnePunchManWeb.model.Heroe;
import OnePunchManWeb.model.HeroeXVideojuego;
import OnePunchManWeb.model.Monstruo;
import OnePunchManWeb.model.Patrocinador;
import OnePunchManWeb.model.Videojuego;
import OnePunchManWeb.model.Visita;
import OnePunchManWeb.model.VisitaXComida;
import OnePunchManWeb.service.IComidaService;
import OnePunchManWeb.service.IHerXVidService;
import OnePunchManWeb.service.IHeroeService;
import OnePunchManWeb.service.IMonstruoService;
import OnePunchManWeb.service.IPatrocinadorService;
import OnePunchManWeb.service.IVideojuegoService;
import OnePunchManWeb.service.IVisXComService;
import OnePunchManWeb.service.IVisitaService;

@Controller
public class HomeController {
	@Autowired
	IComidaService com;
	@Autowired
	IVisitaService vis;
	@Autowired
	IVisXComService vxc;
	@Autowired
	IHeroeService hero;
	@Autowired
	IVideojuegoService vid;
	@Autowired
	IHerXVidService hxv;
	@Autowired
	IPatrocinadorService patro;
	@Autowired
	IMonstruoService mons;

	@RequestMapping(value= "/", method = RequestMethod.GET)
	public String HomePage() {
		return "home";
	}

	@RequestMapping(value= "/listarComidas", method = RequestMethod.GET)
	public String ListarComidas(Model modelo) {
		List<Comida> comidas= com.listarComidas();
		modelo.addAttribute("comidas",comidas);
		return "listarComidas";
	}


	//Controladores para insertar regiones
	@RequestMapping(value = "/insertarComidas")
	public String InsertarComidas() {
		return "insertarComidas";
	}

	@PostMapping(value = "/guardarComidas")
	public String guardarComidas(@ModelAttribute Comida comida, BindingResult result,Model modelo) {
		com.guardar(comida);
		List<Comida> comidas= com.listarComidas();
		modelo.addAttribute("comidas",comidas);
		return "listarComidas";
	}
	//Fin controladores para insertar regiones

	/**
	 * @RequestMapping(value = "/eliminarComida/{id}")
	public String eliminarComida(@PathVariable("id") int id,Model modelo) {
		com.eliminar(id);
		List<Comida> comidas=com.listarComidas();
		modelo.addAttribute("comidas",comidas);
		return "redirect:/listarComidas";
	}
	 */

	@RequestMapping(value="/editarComida/{id}")
	public String editarComida(@PathVariable("id") int id,Model modelo) {
		Comida eldato=com.encontrarPorId(id);
		modelo.addAttribute("comida",eldato);
		return "editarComidas";
	}

	@PostMapping(value="/editarComida/guardarNuevaComida")
	public String guardarNuevaComida(Model modelo, @RequestParam("id") int id, @RequestParam("nombre") 
	String nombre)
	{
		Comida eldato=com.encontrarPorId(id);
		eldato.setId(id);
		eldato.setNombre(nombre);
		com.guardar(eldato);
		List<Comida> comidas=com.listarComidas();
		modelo.addAttribute("comidas",comidas);
		return "redirect:/listarComidas";
	}

	@RequestMapping(value= "/listarVisitas", method = RequestMethod.GET)
	public String ListarVisitas(Model modelo) {
		List<Visita> visitas= vis.listarVisitas();
		modelo.addAttribute("visitas",visitas);
		return "listarVisitas";
	}

	@RequestMapping(value= "/listarComidaDeVisitas", method = RequestMethod.GET)
	public String ListarComidaDeVisitas(Model modelo) {
		List<Comida> comidaDeVisitas= com.listarComidasPorVisita();
		List<Visita> visitasConComida= vis.listarVisitasYComida();
		modelo.addAttribute("comidaDeVisitas",comidaDeVisitas);
		modelo.addAttribute("visitasConComida", visitasConComida);
		return "listarComidaDeVisitas";
	}

	//Controladores para insertar regiones
	@RequestMapping(value = "/insertarVisitas")
	public String InsertarVisitas(Model modelo) {
		List<Comida> comidas= com.listarComidas();
		modelo.addAttribute("comidas",comidas);
		return "insertarVisitas";
	}

	@PostMapping(value = "/guardarVisitas")
	public String guardaVisitas(Model modelo, @RequestParam("id") int id,
			@RequestParam("descripcion") String descripcion,
			@RequestParam("fecha") String fecha,
			@RequestParam("comidas[]") List<Integer> id_comidas) {
		Visita visita= new Visita(id,descripcion,fecha);
		vis.guardar(visita);
		VisitaXComida visxcom;
		for (int i = 0; i < id_comidas.size(); i++) {
			Comida comida= com.encontrarPorId(id_comidas.get(i).intValue());
			visxcom= new VisitaXComida(visita,comida);
			vxc.guardar(visxcom);
		}
		List<Visita> visitas= vis.listarVisitas();
		modelo.addAttribute("visitas",visitas);
		return "listarVisitas";
	}
	//Fin controladores para insertar regiones

	/**
	 * @RequestMapping(value = "/eliminarVisita/{id}")
	public String eliminarVisita(@PathVariable("id") int id,Model modelo) {
		vis.eliminar(id);
		List<Visita> visitas=vis.listarVisitas();
		modelo.addAttribute("visitas",visitas);
		return "redirect:/listarVisitas";
	}
	 * 
	 */

	@RequestMapping(value="/editarVisita/{id}")
	public String editarVisita(@PathVariable("id") int id,Model modelo) {
		Visita eldato=vis.encontrarPorId(id);
		modelo.addAttribute("visita",eldato);
		return "editarVisitas";
	}

	@PostMapping(value="/editarVisita/guardarNuevaVisita")
	public String guardarNuevaVisita(Model modelo, @RequestParam("id") int id, 
			@RequestParam("descripcion") String descripcion,
			@RequestParam("fecha") String fecha)
	{
		Visita eldato=vis.encontrarPorId(id);
		eldato.setId(id);
		eldato.setDescripcion(descripcion);
		eldato.setFecha(fecha);
		vis.guardar(eldato);
		return "redirect:/listarVisitas";
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

	@RequestMapping(value= "/listarVideojuegos", method = RequestMethod.GET)
	public String ListarVideojuegos(Model modelo) {
		List<Videojuego> videojuegos= vid.listarVideojuegos();
		modelo.addAttribute("videojuegos",videojuegos);
		return "listarVideojuegos";
	}


	//Controladores para insertar regiones
	@RequestMapping(value = "/insertarVideojuegos")
	public String InsertarVideojuegos() {
		return "insertarVideojuegos";
	}

	@PostMapping(value = "/guardarVideojuegos")
	public String guardarVideojuegos(@ModelAttribute Videojuego videojuego, BindingResult result,Model modelo) {
		vid.guardar(videojuego);
		List<Videojuego> videojuegos= vid.listarVideojuegos();
		modelo.addAttribute("videojuegos",videojuegos);
		return "listarVideojuegos";
	}
	//Fin controladores para insertar regiones


	@RequestMapping(value = "/eliminarVideojuego/{id}")
	public String eliminarVideojuego(@PathVariable("id") int id,Model modelo) {
		vid.eliminar(id);
		List<Videojuego> videojuegos=vid.listarVideojuegos();
		modelo.addAttribute("videojuegos",videojuegos);
		return "redirect:/listarVideojuegos";
	}

	@RequestMapping(value="/editarVideojuego/{id}")
	public String editarVideojuego(@PathVariable("id") int id,Model modelo) {
		Videojuego eldato=vid.encontrarPorId(id);
		modelo.addAttribute("videojuego",eldato);
		return "editarVideojuegos";
	}

	@PostMapping(value="/editarVideojuego/guardarNuevoVideojuego")
	public String guardarNuevoVideojuego(Model modelo, @RequestParam("id") int id, @RequestParam("nombre") 
	String nombre)
	{
		Videojuego eldato=vid.encontrarPorId(id);
		eldato.setId(id);
		eldato.setNombre(nombre);
		vid.guardar(eldato);
		List<Videojuego> videojuegos=vid.listarVideojuegos();
		modelo.addAttribute("videojuegos",videojuegos);
		return "redirect:/listarVideojuegos";
	}

	@RequestMapping(value= "/listarPartidas", method = RequestMethod.GET)
	public String ListarPartidas(Model modelo) {
		List<HeroeXVideojuego> partidas=hxv.listarPartidas();
		modelo.addAttribute("partidas",partidas);
		return "listarPartidas";
	}


	//Controladores para insertar regiones
	@RequestMapping(value = "/insertarPartidas")
	public String InsertarPartidas(Model modelo) {
		List<Heroe> heroes= hero.listarHeroes();
		List<Videojuego> videojuegos= vid.listarVideojuegos();
		modelo.addAttribute("videojuegos", videojuegos);
		modelo.addAttribute("heroes", heroes);
		return "insertarPartidas";
	}

	@PostMapping(value = "/guardarPartidas")
	public String guardarPartidas(Model modelo, @RequestParam("fecha") 
	String fecha, @RequestParam("ganador") int id_ganador, 
	@RequestParam("perdedor") int id_perdedor,
	@RequestParam("videojuego") int id_videojuego) {
		Heroe ganador= hero.encontrarPorId(id_ganador);
		Heroe perdedor= hero.encontrarPorId(id_perdedor);
		Videojuego videojuego= vid.encontrarPorId(id_videojuego);
		HeroeXVideojuego partida= new HeroeXVideojuego(fecha,ganador,perdedor,videojuego);
		hxv.guardar(partida);
		List<HeroeXVideojuego> partidas= hxv.listarPartidas();
		modelo.addAttribute("partidas",partidas);
		return "listarPartidas";
	}
	//Fin controladores para insertar regiones

	@RequestMapping(value = "/eliminarPartida/{id}")
	public String eliminarPartida(@PathVariable("id") int id,Model modelo) {
		hxv.eliminar(id);
		List<HeroeXVideojuego> partidas=hxv.listarPartidas();
		modelo.addAttribute("partidas",partidas);
		return "redirect:/listarPartidas";
	}

	@RequestMapping(value="/editarPartida/{id}")
	public String editarPartida(@PathVariable("id") int id,Model modelo) {
		HeroeXVideojuego eldato=hxv.encontrarPorId(id);
		List<Heroe> heroes= hero.listarHeroes();
		List<Videojuego> videojuegos= vid.listarVideojuegos();
		modelo.addAttribute("partida",eldato);
		modelo.addAttribute("heroes", heroes);
		modelo.addAttribute("videojuegos",videojuegos);
		return "editarPartidas";
	}

	@PostMapping(value="/editarPartida/guardarNuevaPartida")
	public String guardarNuevaPartida(Model modelo, @RequestParam("id") int id, @RequestParam("fecha") 
	String fecha, @RequestParam("ganador") int id_ganador, @RequestParam("perdedor") int id_perdedor,
	@RequestParam("videojuego") int id_videojuego)
	{
		HeroeXVideojuego eldato=hxv.encontrarPorId(id);
		eldato.setId(id);
		eldato.setFecha(fecha);
		Heroe ganador= hero.encontrarPorId(id_ganador);
		Heroe perdedor= hero.encontrarPorId(id_perdedor);
		Videojuego videojuego= vid.encontrarPorId(id_videojuego);
		eldato.setGanador(ganador);
		eldato.setPerdedor(perdedor);
		eldato.setVideojuego(videojuego);
		hxv.guardar(eldato);
		List<HeroeXVideojuego> partidas=hxv.listarPartidas();
		modelo.addAttribute("partidas", partidas);
		return "redirect:/listarPartidas";
	}
	
	@RequestMapping(value= "/listarPatrocinadores", method = RequestMethod.GET)
	public String ListarPatrocinadores(Model modelo) {
		List<Patrocinador> patrocinadores= patro.listarPatrocinadores();
		modelo.addAttribute("patrocinadores",patrocinadores);
		return "listarPatrocinadores";
	}


	//Controladores para insertar Patrocinador
	@RequestMapping(value = "/insertarPatrocinadores")
	public String InsertarPatrocinadores() {
		return "insertarPatrocinadores";
	}

	@PostMapping(value = "/guardarPatrocinadores")
	public String guardarPatrocinadores(@ModelAttribute Patrocinador patrocinador, BindingResult result,Model modelo) {
		patro.guardar(patrocinador);
		List<Patrocinador> patrocinadores= patro.listarPatrocinadores();
		modelo.addAttribute("patrocinadores",patrocinadores);
		return "listarPatrocinadores";
	}
	//Fin controladores para insertar Patrocinador

	 @RequestMapping(value = "/eliminarPatrocinador/{id}")
	public String eliminarPatrocinador(@PathVariable("id") int id,Model modelo) {
		patro.eliminar(id);
		List<Patrocinador> patrocinadores=patro.listarPatrocinadores();
		modelo.addAttribute("patrocinadores",patrocinadores);
		return "redirect:/listarPatrocinadores";
	}
	 

	@RequestMapping(value="/editarPatrocinador/{id}")
	public String editarPatrocinador(@PathVariable("id") int id,Model modelo) {
		Patrocinador eldato=patro.encontrarPorId(id);
		modelo.addAttribute("patrocinador",eldato);
		return "editarPatrocinadores";
	}

	@PostMapping(value="/editarPatrocinador/guardarNuevoPatrocinador")
	public String guardarNuevoPatrocinador(Model modelo, @RequestParam("id") int id, @RequestParam("nombre") 
	String nombre,  @RequestParam("dinero") int dinero)
	{
		Patrocinador eldato=patro.encontrarPorId(id);
		eldato.setId(id);
		eldato.setNombre(nombre);
		eldato.setDinero(dinero);
		patro.guardar(eldato);
		List<Patrocinador> patrocinadores=patro.listarPatrocinadores();
		modelo.addAttribute("patrocinadores",patrocinadores);
		return "redirect:/listarPatrocinadores";
	}
	
	@RequestMapping(value= "/listarMonstruos", method = RequestMethod.GET)
	public String ListarMonstruos(Model modelo) {
		List<Monstruo> monstruos= mons.listarMonstruos();
		modelo.addAttribute("monstruos",monstruos);
		return "listarMonstruos";
	}


	//Controladores para insertar Monstruo
	@RequestMapping(value = "/insertarMonstruos")
	public String InsertarMonstruos() {
		return "insertarMonstruos";
	}

	@PostMapping(value = "/guardarMonstruos")
	public String guardarMonstruos(@ModelAttribute Monstruo monstruo, BindingResult result,Model modelo) {
		mons.guardar(monstruo);
		List<Monstruo> monstruos= mons.listarMonstruos();
		modelo.addAttribute("monstruos",monstruos);
		return "listarMonstruos";
	}
	//Fin controladores para insertar Monstruo

	 @RequestMapping(value = "/eliminarMonstruo/{id}")
	public String eliminarMonstruo(@PathVariable("id") int id,Model modelo) {
		mons.eliminar(id);
		List<Monstruo> monstruos=mons.listarMonstruos();
		modelo.addAttribute("monstruos",monstruos);
		return "redirect:/listarMonstruos";
	}
	 

	@RequestMapping(value="/editarMonstruo/{id}")
	public String editarMonstruo(@PathVariable("id") int id,Model modelo) {
		Monstruo eldato=mons.encontrarPorId(id);
		modelo.addAttribute("monstruo",eldato);
		return "editarMonstruos";
	}

	@PostMapping(value="/editarMonstruo/guardarNuevoMonstruo")
	public String guardarNuevoMonstruo(Model modelo, @RequestParam("id") int id, @RequestParam("nombre") 
	String nombre,  @RequestParam("amenaza") String amenaza,  @RequestParam("tiene_celula") String tiene_celula)
	{
		Monstruo eldato=mons.encontrarPorId(id);
		eldato.setId(id);
		eldato.setNombre(nombre);
		eldato.setAmenaza(amenaza);
		eldato.setTiene_celula(tiene_celula);
		mons.guardar(eldato);
		List<Monstruo> monstruos=mons.listarMonstruos();
		modelo.addAttribute("monstruos",monstruos);
		return "redirect:/listarMonstruos";
	}
}
