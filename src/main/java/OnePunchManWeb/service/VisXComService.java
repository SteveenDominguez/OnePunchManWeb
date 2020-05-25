package OnePunchManWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import OnePunchManWeb.model.VisitaXComida;
import OnePunchManWeb.repository.VisXComRepository;

@Service
public class VisXComService implements IVisXComService {
	@Autowired
	VisXComRepository vxc;
	
	@Override
	public List<VisitaXComida> listarVisXCom() {
		// TODO Auto-generated method stub
		return vxc.findAll();
	}

	@Override
	public VisitaXComida encontrarPorId(int id) {
		// TODO Auto-generated method stub
		return vxc.findById(id);
	}

	@Override
	public void guardar(VisitaXComida visxcom) {
		// TODO Auto-generated method stub
		vxc.save(visxcom);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		vxc.deleteById(id);
	}
}
