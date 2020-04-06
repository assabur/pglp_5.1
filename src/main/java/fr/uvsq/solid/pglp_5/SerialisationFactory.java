package fr.uvsq.solid.pglp_5;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * classe dans laquelle on implemente les differentes methodes de manipulation de fichiers
 */
public class SerialisationFactory
{
	/*
	 * permet d'ecrire un objet dans un fichier
	 * @param 
	 */
	public Personnels writeFile(Personnels obj, String filename, Flash log) 
	{
		try (ObjectOutputStream	out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(filename)))) {
			out.writeObject(obj);
			log.affiche("Objet " + obj + " créé!");
			return obj;
		} catch (IOException e) {
			log.affiche(e.getMessage());
			return null;
		}
	}
	
	/*
	 * methode qui supprime un fichier à travers son nom
	 */
	
	public void deleteFile(String filename, Flash log) {
		File file = new File(filename); 
        if(file.delete()) log.affiche("Objet supprimé!"); 
        else log.affiche("Suppression impossible");
	}
	
	/*
	 * verifie si un fichier existe auparavant
	 */
	
	private boolean exists(String filename) 
	{
		File file = new File(filename); 
        return file.exists();
	}
	/*
	 * methode qui permet de lire le contenu d'un fichier
	 */
	public Personnels readFile(String filename,Flash log,int id) {
		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(filename)))) {
			
			int test_id=0;boolean seuil=false;
			while( in.readObject() !=null)
			{
				Personnels obj = (Personnels) in.readObject();
				 test_id =obj.getid();
				 if(test_id==id)
				 {
					 log.affiche("Objet "+" trouvé!");
					 return obj;   
				 }
			}
		return null;
		} catch (ClassNotFoundException | IOException e) {
			log.affiche(e.getMessage());
			return null;
		}
	}
	/*
	 * méthode de création de fichier
	 */
	public Personnels createFile(Personnels obj, String filename, Flash log) {
		if (exists(filename)) {
			log.affiche("Création impossible");
			return null;
		}
		else {
			writeFile(obj, filename, log);
			return obj;
		}
	}
	/*
	 * mise à jour d'un object dans le fichier
	 */
	public Personnels updateFile(Personnels obj, String filename, Flash log) {
		if (!exists(filename)) {
			log.affiche("Mise à jour impossible");
			return null;
		}
		else {
			deleteFile(filename, log);
			writeFile(obj, filename, log);
			return obj;
		}
	}
}
