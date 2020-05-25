package OnePunchManWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import OnePunchManWeb.model.Combate;
import OnePunchManWeb.repository.CombateRepository;

@Service
public class CombateService implements ICombateService {
	@Autowired
	CombateRepository comba;
	@Override
	public List<Combate> listarCombates() {
		// TODO Auto-generated method stub
		return comba.findAll();
	}

	@Override
	public Combate encontrarPorId(int id) {
		// TODO Auto-generated method stub
		return comba.findById(id);
	}

	@Override
	public void guardar(Combate combate) {
		// TODO Auto-generated method stub
		comba.save(combate);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		comba.deleteById(id);
	}

}
