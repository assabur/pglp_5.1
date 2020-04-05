package fr.uvsq.solid.pglp_5;
/*
 * classe abstraite des differentes methodes du CRUD
 */
public abstract class DAO<T> {
	public abstract T create (T obj);
	public abstract T find (String id);
	public abstract T update (T obj);
	public abstract void delete(T obj);
}
