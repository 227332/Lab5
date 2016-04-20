package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.polito.tdp.db.ParolaDAO;

public class RuzzleModel {
	//siccome ruzzle sarà sempre 4x4, definisco la dim come static
	private static int ruzzleMatrixDim = 4 ;
	private char[][] matrix= new char[ruzzleMatrixDim][ruzzleMatrixDim];
	static List<Parola> elenco = new ArrayList<Parola>();
	

	public int getMatrixDim() {
		// TODO Auto-generated method stub
		return ruzzleMatrixDim;
	}
	
	

	public char[][] generateRandomMatrix() {
		// TODO Auto-generated method stub
		
		//Random è una classe di java che genera numeri casuali
		//in particolare ha il metodo nextInt(int n) che restituisce un numero intero casuale
		//tra 0 incluso e n escluso
		Random r= new Random();
		for (int i = 0; i < ruzzleMatrixDim; i++) {
			for (int j = 0; j < ruzzleMatrixDim; j++) {
				char c;
				do {
					//ora genero un numero da 0 a 25 perchè le lettere dell'alfabeto sono
					//in totale 26. Poi sommo tale numero al numero ASCII corrispondente ad a
					//perchè così sono sicura di avere un intero che è l'ASCII corrispondente
					//di una lettera
					c = (char) (r.nextInt(26) + 'A'); 
				} while (c!='X' && c!='W' && c!='Y' && c!='J' && c!='K');
				matrix[i][j]=c;
			}
		}
		return matrix;
	}
	
	/*METODO ALTERNATIVO SIMILE MA FURBO:
	  public char[][] generateRandomMatrix() {
		
		String abc = "ABCDEFGHILMNOPQRSTUVZ";
		Random r= new Random();
		for (int i = 0; i < ruzzleMatrixDim; i++) {
			for (int j = 0; j < ruzzleMatrixDim; j++) 
				matrix[i][j]=(char)(r.nextInt(26) + 'a');
				
		return matrix;
	}

	 */


	
	public void solveRuzzle() {
		// TODO Auto-generated method stub
		
		// Delete previous solutions
		elenco.clear();
		
		List<Position> usedPositions = new ArrayList<Position>();
		String currentWord = ""; //??oppure null???

		recursiveRuzzleSolver(currentWord, usedPositions, new Position(-1, -1), 0);

				
	}


	private void recursiveRuzzleSolver(String currentWord, List<Position> usedPositions, Position pos,
			int level) {
		// TODO Auto-generated method stub
		
		//gestione della soluzione completata
		if (level > 2 && isPresent(currentWord)) {

			Parola word = new Parola(currentWord);
			//word.getCharacters().addAll(currentWord);
			word.getPositions().addAll(usedPositions);
			elenco.add(word);
		}
		
		// Condizione di terminazione
		if (level == ruzzleMatrixDim * ruzzleMatrixDim) {
			return;
		}
		
		// Ottieni i vicini per la posizione corrente, tenendo conto che i caratteri in
		//charsPositions sono già stati usati
		List<Position> neighbours = getNeighbours(pos, usedPositions);
		for (Position p : neighbours) {

		// Add char 
		//??si può fare + con un char?? Se non si può allora usa String.valueOf()
		currentWord=currentWord+matrix[p.getX()][p.getY()];
		usedPositions.add(p);

		recursiveRuzzleSolver(currentWord, usedPositions, p, level + 1);

		// Backtrack: remove char

		//to do so I simply take the substring from 0 to N-2, because all the string
		//goes from 0 to N-1
		currentWord=currentWord.substring(0, currentWord.length()-1);
		usedPositions.remove(p);
	}

	}
	
	private List<Position> getNeighbours(Position pos, List<Position> usedPositions) {
		// TODO Auto-generated method stub
		
		List<Position> neighbours = new ArrayList<Position>();

		// Se sono al livello 0 della ricorsione aggiungi tutte le celle
		// della matrice, in quanto non sono ancora su alcuna casella di Ruzzle
		// e voglio trovare tutte le parole usando di volta in volta come iniziale tutti
		// i char presenti in ruzzle
		if (pos.getX() == -1 && pos.getY() == -1) {
			for (int i = 0; i < getMatrixDim(); i++)
				for (int j = 0; j < getMatrixDim(); j++)
					neighbours.add(new Position(i, j));
			return neighbours;
		}

		// Altrimenti cerca tutti i vicini per la posizione specificata
		for (int i = pos.getX() - 1; i < pos.getX() + 1; i++)
			for (int j = pos.getY() - 1; j < pos.getY() + 1; j++)
				if (i >= 0 && j >= 0 && i < getMatrixDim() && j < getMatrixDim()) {
					Position p = new Position(i, j);
					if (!usedPositions.contains(p)) {
						neighbours.add(p);
					}
				}

		return neighbours;
	}



	/*
	 * Controlla se la parola è presente nel dizionario
	 */
	public static boolean isPresent(String word) {

		ParolaDAO dao = new ParolaDAO();

		// Controlla l'intera parola
		return dao.searchWord(word);
	}



	public List<Parola> getSolutions() {
		// TODO Auto-generated method stub
		return elenco;
	}



	

}
