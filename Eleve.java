public class Eleve {
 String nom ; 
 String prenom ;
 String classe ;
 int id ;
 public Eleve(int id) {
	 this.id=id;
	 }
 
 public Eleve(String nom,String prenom,String classe) {
	 this.prenom=prenom;
	 this.nom=nom;
	 this.classe=classe;
 }
 public Eleve(int id,String nom,String prenom,String classe) {
	 this.id=id;
	 this.prenom=prenom;
	 this.nom=nom;
	 this.classe=classe;
 }
 public int getId() {
	    return id;
	  }
 public String getPrenom() {
	    return prenom;
	  }
 public String getNom() {
	    return nom;
	  }
 public String getClasse() {
	    return classe;
	  }

}
