package OnePunchManWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import OnePunchManWeb.model.Combate;

@Repository
public interface CombateRepository extends JpaRepository<Combate, Integer> {
	Combate findById(int id);
	List<Combate> findByFecha(String fecha);
	
	}
