package OnePunchManWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import OnePunchManWeb.model.Heroe;

@Repository
public interface HeroeRepository extends JpaRepository<Heroe, Integer> {
	Heroe findById(int id);
	List<Heroe> findByNombre(String nombre);
}