package OnePunchManWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import OnePunchManWeb.model.HeroeXVideojuego;
import OnePunchManWeb.repository.HerXVidRepository;

@Service
public class HerXVidService implements IHerXVidService {
	@Autowired
	HerXVidRepository hxp;
	@Override
	public List<HeroeXVideojuego> listarPartidas() {
		// TODO Auto-generated method stub
		return hxp.findAll();
	}

	@Override
	public HeroeXVideojuego encontrarPorId(int id) {
		// TODO Auto-generated method stub
		return hxp.findById(id);
	}

	@Override
	public void guardar(HeroeXVideojuego herxpar) {
		// TODO Auto-generated method stub
		hxp.save(herxpar);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		hxp.deleteById(id);
	}

}
