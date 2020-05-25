package OnePunchManWeb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="combate")
public class Combate {
	@Id
	private int ID;
	private String fecha;
	private String perdedor;
	private String ganador;
	
	public int getId() {
		return ID;
	}
	public void setId(int iD) {
		ID = iD;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getPerdedor() {
		return perdedor;
	}
	public void setPerdedor(String perdedor) {
		this.perdedor = perdedor;
	}
	public String getGanador() {
		return ganador;
	}
	public void setGanador(String ganador) {
		this.ganador = ganador;
	}
	public Combate(int iD, String fecha, String perdedor, String ganador) {
		super();
		ID = iD;
		this.fecha = fecha;
		this.perdedor = perdedor;
		this.ganador = ganador;
	}
	public Combate() {
		super();
	}
	
	
}
