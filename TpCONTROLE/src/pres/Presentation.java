package pres;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Scanner;

import dao.DaoImpl;
import dao.IDao;
import metier.IMetier;
import metier.MetierImpl;

public class Presentation {
	
	public static void main(String[] args) throws Exception {
		
		/*Injection des dependances par 
		 * instanciation statique => new */
		/*
			DaoImpl dao = new DaoImpl();
			MetierImpl metier = new MetierImpl();
			metier.setDao(dao);
			System.out.println(metier.calcul());
		*/

		Scanner scanner = new Scanner(new File("config.txt"));
		
		String daoCLasseName = scanner.nextLine();
		Class cDao = Class.forName(daoCLasseName);
		IDao dao = (IDao)cDao.newInstance();
		
		String metierCLasseName = scanner.nextLine();
		Class cMetier = Class.forName(metierCLasseName);
		IMetier metier = (IMetier)cMetier.newInstance();
		
		Method methode = cMetier.getMethod("setDao", IDao.class);
		methode.invoke(metier, dao);
		
		System.out.println(metier.calcul());
		
	}

}
