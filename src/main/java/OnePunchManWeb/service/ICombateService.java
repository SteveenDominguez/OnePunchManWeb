package OnePunchManWeb.service;

import java.util.List;

import OnePunchManWeb.model.Combate;



public interface ICombateService {
	List<Combate> listarCombates();
	Combate encontrarPorId(int id);
	void guardar(Combate combate);
	void eliminar(int id);
	
}
