package tienda.discos.datos.serviciosWeb;

import java.util.List;
import java.util.Map;

public class InfoDiscos {
	private List<Map<String, Object>> discos;
	private int totalDiscos;
	

	public List<Map<String, Object>> getDiscos() {
		return discos;
	}
	public void setDiscos(List<Map<String, Object>> discos) {
		this.discos = discos;
	}
	public int getTotalDiscos() {
		return totalDiscos;
	}
	public void setTotalDiscos(int totalDiscos) {
		this.totalDiscos = totalDiscos;
	}
}
