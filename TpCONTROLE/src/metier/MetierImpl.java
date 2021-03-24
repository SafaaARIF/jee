package metier;

import dao.IDao;

public class MetierImpl implements IMetier{
	/*couplage faible */
	private IDao dao;

	@Override
	public double calcul() {
		double data=dao.getData();
		double res=data*Math.PI;
		return res;
	}

	/*Pour injecter dans dao un objet d'une classe
	 * qui implemente IDao*/
	public void setDao(IDao dao) {
		this.dao = dao;
		System.out.println("Injection de dependances");
	}
	
	public void init() {
		System.out.println("Initialisation de Metier Implementation");
	}

	public MetierImpl() {
		System.out.println("Instanciation de métier");
	}
	

}
