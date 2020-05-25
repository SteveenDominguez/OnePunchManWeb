package OnePunchManWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import OnePunchManWeb.model.Patrocinador;
import OnePunchManWeb.repository.PatrocinadorRepository;

@Service
public class PatrocinadorService implements IPatrocinadorService {
	@Autowired
	PatrocinadorRepository patro;
	@Override
	public List<Patrocinador> listarPatrocinadores() {
		// TODO Auto-generated method stub
		return patro.findAll();
	}

	@Override
	public Patrocinador encontrarPorId(int id) {
		// TODO Auto-generated method stub
		return patro.findById(id);
	}

	@Override
	public void guardar(Patrocinador patrocinador) {
		// TODO Auto-generated method stub
		patro.save(patrocinador);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		patro.deleteById(id);
	}

}
