
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class BD {
	private Eleve eleve =new Eleve(0);
	private   String nom ;
	private   String prenom ;
	private   String classe ;
	private  static String url = "jdbc:postgresql://localhost:5432/Ecole";
	private  static String user ="postgres";
	private  static String passwd ="POSTGRES";
	private static Connection connect;
	
	
	public ArrayList<String[]> getEleves(){
		return null;
	}
	
	public ArrayList<String> getNomsClasses() {
		return null;
	}
	
	public static Connection getInstance() {
		if(connect==null) 
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			
		} catch (Exception e) {
			e.printStackTrace();
	
}
		return connect;
		
}
	public void afficher() {
		try {
			Statement state = BD.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM eleve");
			System.out.println("ID       NOM          PRENOM      CLASSE" );
			System.out.println("********************************");
			while(result.next()) {
				
				System.out.print(""+result.getString(1)+"        ");
				System.out.print(""+result.getString(2)+"        ");
				System.out.print(""+result.getString(3)+"        ");
				System.out.print(""+result.getString(4)+"        ");
				System.out.println("\n                             ");
				
			}
			result.close();
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean ajouterEleve(Eleve eleve) {
	    PreparedStatement st;
	    try {
	        st = BD.getInstance().prepareStatement("INSERT INTO eleve (elev_nom, elev_prenom, elev_classe) VALUES (?, ?, ?)");
	        st.setString(1, eleve.getNom());
	        st.setString(2, eleve.getPrenom());
	        st.setString(3, eleve.getClasse());
	        st.executeUpdate();
	        st.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; 
	    }
	    return true;
	}
	
	 public boolean modifierEleve(Eleve eleve) {
		return false;
		 
	 }
	 public boolean supprimerEleve(Eleve eleve) {
			return false;
			 
		 }
	public Object getEleve() {
		List<Object[]> list = new ArrayList<>();
		Statement stat;
		try {
			stat = BD.getInstance().createStatement();
			ResultSet resul = stat.executeQuery("SELECT * FROM eleve");
			while(resul.next()) {	
				list.add(new Object[] { resul.getString(1),resul.getString(2),resul.getString(3) });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
}
