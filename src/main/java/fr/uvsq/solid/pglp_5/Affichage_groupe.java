package fr.uvsq.solid.pglp_5;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
/*
 * classe qui gere l'affichage par groupe
 */
public class Affichage_groupe implements Serializable
{
	/*
	 * declaration des attributs
	 */
	private ArrayList<Interface_Personnel> tab =new ArrayList<Interface_Personnel>();
	/*
	 * methode qui prend en entré la racine de notre graphe d'hierarchie
	 */
	public Affichage_groupe (Interface_Personnel root)
	{
		this.tab.add(root);
	}
	
	/*
	 * methode qui retourne un nouveau iterateur
	 */
	public Iterator getIterator ()
	{
		return new GroupeIterator();
		
	}
	private class GroupeIterator implements Iterator
	{
		/*
		 * parcourir l'architecture en verifiant a chaque fois que l'instance est un composite ou pas
		 */
		public  GroupeIterator ()
		{
			int index =0;
			while (index < tab.size())
			{	
				if	(tab.get(index) instanceof Composite_Personnel)
				{	
					 for (Interface_Personnel personne : ((Composite_Personnel)tab.get(index)).personnel )
					{ 		      
				          tab.add(personne);		
				    }				
				}				
				index ++;
			}
		}
		int i=0;

		 /*
		 * methode qui verifie si il existe un suivant dans la hierarchie
		 */
		public boolean hasNext() 
		{
			if (i < tab.size())			
			 return true;			
			 return false;
		}

		/*
		 * methode qui renvoie le prochain personnel dans la hierarchie
		 */
		public Interface_Personnel Next() {
			
			if (this.hasNext()){
				i++;
			return tab.get(i-1);
			}
			return null;
		}	
	}
		/* 
		 * methode implementant la serialisation d'une hierarchie
		 * on initialise notre fichier des valeurs de notre tableau contenant des personnes
		 */
		public void serializable_Personnel()
		{	
			//fichier de sauvegarde
			File fichier =  new File("personne.save") ;

			 // ouverture d'un flux sur un fichier
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
				 int index =0;
					while (index <tab.size())
					{	
						if	(tab.get(index) instanceof Composite_Personnel)
						{	
							 for (Interface_Personnel personne : ((Composite_Personnel)tab.get(index)).personnel )
							{ 		      
								 oos.writeObject(personne );
						    }				
						}
					   oos.writeObject(((Composite_Personnel)tab.get(index)).personnel );	
					   index ++;
					}
					System.out.println("Serialisation terminée");
					oos.close();
			} catch (FileNotFoundException e) {
				 System.out.printf(" fichier non trouvé");
				e.printStackTrace();
			} catch (IOException e) {
				 System.out.printf("erreur d'ecriture dans le fichier personnel");
				e.printStackTrace();
			}
			
}
		
		/* 
		 * methode implementant la deserialisation d'une hierarchie
		 * on recupere les personnes dans notre fichier 
		 */
		public void deserializable_Personnel()
		{
			Interface_Personnel personne;			
				
			try {
				 FileInputStream fileIn = new FileInputStream("personne.save");
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         
		         do
		         {
		        	 personne =(Interface_Personnel) in.readObject();
		        	 personne.affiche(); 
		         }while(personne!=null);
		         in.close();
				 fileIn.close();
		       
		      } 
			catch (IOException i)
			  {
				System.out.println("Terminé");
		         i.printStackTrace(); 
		      } 
			
			 catch (ClassNotFoundException c) 
			{
		         System.out.println("personnel non trouvé");
		         c.printStackTrace();
		    }
			finally
			{
				
			}
			
	   }
					
}
