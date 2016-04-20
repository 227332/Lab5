package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * classe a cui affido il compito della connessione
 */


public class DBConnect {

//tale variabile è:
//-static se no non posso usarlo in getConnection() che ho implementato qui
//-final perchè è un attributo costante, non lo cambierò mai
private static final String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root";



//lo dichiaro static perchè mi viene comodo in 
//quanto così posso invocarlo direttamente sul
//nome della classe, senza prima instanziarla
public static Connection getConnection(){
	Connection conn;
	try{
		conn=DriverManager.getConnection(jdbcURL);
	} catch (SQLException e){
		/*
		 * e.printStackTrace(); NON è BELLO DA VEDERE, OCCORRE 
		 * SEMPRE GESTIRE LE ECCEZIONI PER BENE UNA VOLTA CATTURATE
		 */
		//voglio gestire tale eccezione: qui la gestisco lanciandola
		//al metodo chiamante (ossia a quello che ha chiamato tale 
		//metodo quando ha generato l'eccezione, così da farlo 
		//gestire da lui
		throw new RuntimeException("Errore nella connessione",e);
	}
	return conn;
}


}