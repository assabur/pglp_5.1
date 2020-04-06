package fr.uvsq.solid.pglp_5;
/*
 * classe permettant de creeer une nouvelle instance de personell DAO
 */
public class FabriqueDAO
{
	/*
	 * methode d'acces statique à une nouvelle instance de PersonnelDAO
	 */
	public static DAO<Personnels> getPersonneDAO()
	{
		return new PersonnelDao();
	}
}
