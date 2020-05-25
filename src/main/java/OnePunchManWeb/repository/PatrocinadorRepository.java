package OnePunchManWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import OnePunchManWeb.model.Patrocinador;
@Repository
public interface PatrocinadorRepository extends JpaRepository<Patrocinador, Integer> {
	Patrocinador findById(int id);
	List<Patrocinador> findByNombre(String nombre);
	
	}
