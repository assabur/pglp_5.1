package fr.uvsq.solid.pglp_5;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
/*
 * definition de la classe personnel
 */
public final class  Personnels implements Interface_Personnel,Serializable  {
	
	/*
	 * constructeur prend en entré un builder
	 */
	private Personnels(Builder builder)
	{
		  //parametres requis 
		   id=builder.id;
		   nom=builder.nom;
		   prenom=builder.prenom;	 
		  
		  //parametre optionels
		    telephone =builder.telephone;	  
		    fonction=builder.fonction;
		    date_naiss=builder.date_naiss;
	}
	
	 /*
	   * methode qui retourne le id d'un personnel
	   */
	 public int getid()
	  {
		  return this.id;
	  }
		/*
		 * définition des parametres requis de notre builder
		 */
		 private int id;
		 private String nom;
		 private String prenom;
		 private String fonction;
		 private LocalDateTime date_naiss;
		 private ArrayList<Integer> telephone =new ArrayList<Integer>();
		 
		
		 /*
		  * pour changer le nom
		  */
		public void setNom(String nom) {
			this.nom = nom;
		}
		/*
		 * pour changer le prenom
		 */
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

	/*
	 * 	methode d'affichage du personnel de l'hierarchie
	 */
	public void affiche()
	{
		System.out.println("Personnel "+this.id+" :"+" Nom: "+this.nom+" Prenom: "+this.prenom);		
	}
	  /*
	   * redefinissons la methode tostring
	   */
	 public String toString()
	  {	 
		 String chaine ="je suis le personnel "+this.id;
		 return chaine;
	  }
	/*
	 * definition de la classe  builder
	 */
  public static class Builder
  {
	  //parametres requis 
	  private int id;
	  private String nom;
	  private String prenom;	 
  
	  //parametre optionels
	  private LocalDateTime date_naiss;
	  private ArrayList<Integer> telephone =new ArrayList<Integer>();	  
	  private String fonction;
	  
	  /*
	   * Constructeur du builder
	   */
	  public Builder(int id,String nom,String prenom)
	  {
		  this.id=id;
		  this.nom=nom;
		  this.prenom=prenom;  
	  }
	  /*
	   * methode servant a modifier le parametre nom
	   */
	  public Builder name_change(String new_name)
	  {
		  this.nom=new_name;
		  return this;  
	  }
	  /*
	   * methode servant a modifier le parametre prenom
	   */
	  public Builder surname_change(String new_surname)
	  {
		  this.prenom=new_surname;
		  return this;  
	  }
	  /*
	   * methode servant a modifier le parametre optionnel fonction
	   */
	  public Builder fonction_change(String new_fonction)
	  {
		  fonction=new_fonction;
		  return this;  
	  }
	  /*
	   * methode servant a modifier le parametre optionnel numero de telephone
	   */
	  public Builder telephone_change(ArrayList<Integer> new_telephone)
	  {
		  telephone=new_telephone;
		  return this;    
	  }
	  /*
	   * methode qui renvoie un nouveau personnel
	   */
	  public Personnels build()
	  {
		  return new Personnels(this);	  
	  }

  }
}
