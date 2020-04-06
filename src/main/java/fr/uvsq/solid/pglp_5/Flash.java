package fr.uvsq.solid.pglp_5;

import java.time.LocalDateTime;

/*
 * classe qui permet dafficher le retour d'une methode de CRUD par exemple :objet cree
 */
public class Flash {

	public void affiche(String text)
	{
		System.out.println(LocalDateTime.now()+": "+text);
	}
}
