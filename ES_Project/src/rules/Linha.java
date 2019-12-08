package rules;

/**
 * @author Francisco Raimundo
 *
 *Classe criada para conseguir implementar e testar até o 
 *Batoca fazer,
 *sendo no final desnecessária e vou fazer a migração assim que possa.
 *
 */
public class Linha {

	double loc;
	double cyclo;
	double atfd;
	double laa;
	boolean depoisDasRegras;
	public double getLOC() {
		return loc;
	}
	public void setLOC(double loc) {
		this.loc = loc;
	}
	public double getCYCLO() {
		return cyclo;
	}
	public void setCYCLO(double cyclo) {
		this.cyclo = cyclo;
	}
	public double getATFD() {
		return atfd;
	}
	public void setATFD(double atfd) {
		this.atfd = atfd;
	}
	public double getLAA() {
		return laa;
	}
	public void setLAA(double laa) {
		this.laa = laa;
	}
	public boolean getDepoisDasRegras() {
		return depoisDasRegras;
	}
	public void setDepoisDasRegras(boolean depoisDasRegras) {
		this.depoisDasRegras = depoisDasRegras;
	}

}

