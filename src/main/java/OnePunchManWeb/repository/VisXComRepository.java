package OnePunchManWeb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import OnePunchManWeb.model.VisitaXComida;

@Repository
public interface VisXComRepository extends JpaRepository<VisitaXComida, Integer> {
	VisitaXComida findById(int id);
	
}
