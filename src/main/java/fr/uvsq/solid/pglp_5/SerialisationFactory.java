package fr.uvsq.solid.pglp_5;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * classe dans laquelle on implemente les differentes methodes de manipulation de fichiers
 */
public class SerialisationFactory
{	
	/*
	 * construction de la methode Update qui prend en entré l'id verifie l'existence
	 *  de l'objet puis affecte 
	 * les differentes modifications 
	 * 
	 */
	
	public void Update(int id, String filename,Flash log)
	{
		if(fileExists(filename))
		{
			//parcourir le fichier pour rechercher l'id
			if(idExists(id, filename)!=null)
			{
				//je memorise la valeur de l'objet à modifié
				Personnels tamponPersonnel = idExists(id, filename);
				//je modifie les differents champ de mon objet par les nouvelles mises à jour
				tamponPersonnel=miseAjour(tamponPersonnel);

				//je memorise la valeur des objets de mon fichier dans un tableau fichierTampon
				ArrayList<Personnels> fichierTampon= new ArrayList<>();
				//parcours du fichier tout en memorisant les valeurs
				try (ObjectInputStream in = new ObjectInputStream(
						new BufferedInputStream(new FileInputStream(filename)))) 
				{
					
					int test_id=0;boolean seuil=false;
					while( in.readObject() !=null)
					{
						
						Personnels obj = (Personnels) in.readObject();
						//ajout dans le tableau tampon
						if(test_id!=id)
						 {
							fichierTampon.add(obj) ;												
						 }
						//j'enregistre l'objet modifié dans le tableau
						 if(test_id==id)						 
						 fichierTampon.add(tamponPersonnel) ;  
						 			
			        }	
					this.deleteFile(filename, log);
					this.copyInfile(fichierTampon, filename);
				}
				catch (ClassNotFoundException | IOException e)
				{
					log.affiche(e.getMessage());
					
				}
		
	}}}
	/*
	 * supprimme un fichier
	 */
	public void deleteFile(String filename,Flash log)
	{
		File file = new File(filename); 
        if(file.delete()) log.affiche("Objet supprimé!"); 
        else log.affiche("Suppression impossible");
	}
			
		/*
		 * je cree une methode qui recupere le contenu du tableau pour le mettre dans un fichier	
		 */
	
		public void copyInfile(ArrayList<Personnels> tableau,String filename)
		{
			for(Personnels obj :tableau)
			{
				try (ObjectOutputStream	out = new ObjectOutputStream(
						new BufferedOutputStream(new FileOutputStream(filename)))) {
					out.writeObject(obj);
					
				} catch (IOException e) {
					
				}
			}
		}
	
	/*	 
	 * prends en compte les mAJ effectué par l'user		
	 */
			public Personnels miseAjour(Personnels obj)
			{
				 final Scanner reponse=new Scanner(System.in);
				 String chaine="";
				 System.out.println("Tapez 1 pour modifier le nom /n 2 pour modifier le prenom ");
				int rep= reponse.nextInt();
				 switch(rep)
				 {
				 	case(1):
				 	{
				 		System.out.println("entrez le nom ");				 	
				    	chaine=reponse.nextLine();
				    	obj.setNom(chaine);
				 	}break;
				 	case(2):
				 	{
				 		System.out.println("entrez le prenom ");				 	
				    	chaine=reponse.nextLine();
				    	obj.setPrenom(chaine);
				 	}break;
				 	default:break;			 	
				 }			 	
				 		return obj;
			}
								 
							
	/*
	 * verifie l'existence d'un fichier
	 * 
	 **/

	private boolean fileExists(String filename) {

		File file = new File(filename); 

        return file.exists();

	}
	
	/*
	 * verifie si un objet existe dans notre  fichier 
	 */
	
	private Personnels idExists(int id,String filename) 
	{
		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(filename)))) {
			
			int test_id=0;boolean seuil=false;
			while( in.readObject() !=null)
			{
				Personnels obj = (Personnels) in.readObject();
				 test_id =obj.getid();
				 if(test_id==id)
				 {
					 
					 return obj;   
				 }
				
			}
			return null;
		
		} 
		catch (ClassNotFoundException | IOException e) 
		{			
			System.out.println("erreur lors de la verification de l'ID");		
			
		}
		finally
		{
			return null;
		}
		
	}
	
	
	/*
	 * methode de creation d'un objet dans un fichier
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
	 * méthode de création de fichier
	 */
	/*public Personnels createFile(Personnels obj, String filename, Flash log) {
		if (exists(filename)) {
			log.affiche("Création impossible");
			return null;
		}
		else {
			writeFile(obj, filename, log);
			return obj;
		}
	}*/
	/*méthode qui crée un nouveau fichier 
	 *
	public File createFile(Personnels obj,String filename,Flash log)
	{
		if(exix)
	}
	*/
	/*
	 * je verifie si le fichier existe deja
	 */
	public boolean fileExist(String filename)
	{
		File file=new File(filename);
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
	 * methode qui permet de supprimer un objet dans le fichier
	 *
	public Personnels deleteFile(String filename,Flash log,int id) {
	if (exists(id))
	{	try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(filename)))) {
			
			int test_id=0;boolean seuil=false;
		//tant que je ne suis pas a la fin du fichier
			while( in.readObject() !=null)
			{
				Personnels obj = (Personnels) in.readObject();
				 test_id =obj.getid();
				 //si l'objet recuperer est egale à l'objet recherché
				 if(test_id==id)
				 {
					 log.affiche("Objet "+" trouvé!");
					  obj.   
				 }
			}
		return null;
		} catch (ClassNotFoundException | IOException e) {
			log.affiche(e.getMessage());
			return null;
		}
	}
}
	
	
	/*
	 * mise à jour d'un object dans le fichier
	 *
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
	}*/
}
