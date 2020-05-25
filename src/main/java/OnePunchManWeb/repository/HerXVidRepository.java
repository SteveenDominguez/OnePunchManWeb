package OnePunchManWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import OnePunchManWeb.model.HeroeXVideojuego;

@Repository
public interface HerXVidRepository extends JpaRepository<HeroeXVideojuego, Integer> {
	HeroeXVideojuego findById(int id);
}
