package fr.uvsq.solid.pglp_5;
/*
 * classe abstraite des differentes methodes du CRUD
 */
public abstract class DAO<T> 
{
	public abstract T create (Personnels obj);
	public abstract T find (int id);
	public abstract void update (int obj);
	public abstract void delete(T obj);
}
