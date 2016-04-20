package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParolaDAO {

	public boolean searchWord(String word) {
		// TODO Auto-generated method stub
		
		Connection conn=DBConnect.getConnection();
		
		String sql="select nome from parola where nome=?";
		
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, word);
			
			ResultSet res = st.executeQuery();
			
			if (res.next()) {//ossia se c'è una tupla (che è anche l'unica) nella tabella risultante dalla query
				res.close();
				conn.close();
				return true;
			} else {
				res.close();
				conn.close();
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //RIC: qui lascio l'exception di default, solo nel DBConnect
								//la modifico lanciando una eccezione con:
								//throw new RuntimeException("Errore nella connessione",e);
		}
		
		return false;
	}

}
