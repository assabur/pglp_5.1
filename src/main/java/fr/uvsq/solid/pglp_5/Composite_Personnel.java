package fr.uvsq.solid.pglp_5;

import java.io.Serializable;
import java.util.ArrayList;
/*
 * classe qui definit un composite de notre hierarchie 
 */
public class Composite_Personnel implements Interface_Personnel,Serializable {
	private int id;
	public Composite_Personnel(int id)
	{
		this.id=id;
	}
	public ArrayList<Interface_Personnel> personnel=new ArrayList<Interface_Personnel>();
	
	/*
	 * methode qui affiche le personnel
	 */
	public void affiche()
	{	
		for (Interface_Personnel interfaceP: personnel )
		{
			System.out.println("Groupe"+this.id+" contient");
			interfaceP.affiche();
		}
	}
	
	  /*
	   * redefinissons la methode tostring
	   */
	 public String toString()
	 {
		 String chaine ="je suis le composite "+this.id;
		 return chaine;
	 }
	 
	 /*
	  *methode qui ajoute un personnel dans l'hierarchie
	 */	 
	public void add (Interface_Personnel ip)
	{
		personnel.add(ip);
	}
	/*
	 * methode qui supprime un personelle de l'hierarchie
	 */
	public void remove(Interface_Personnel ip)
	{
		personnel.remove(ip);
	}
	
}

