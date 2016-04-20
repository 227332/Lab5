package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.model.Parola;
import it.polito.tdp.model.Position;
import it.polito.tdp.model.RuzzleModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class RuzzleController {

	
	private RuzzleModel model;
	
	//mi creo una lista con tutte le varie Labels in modo da poterle gestire efficientemente
	//con dei cicli
	private List<Label> labels = new ArrayList<Label>();

	public void setModel(RuzzleModel m) {
		// TODO Auto-generated method stub
		model=m;
		
	}
	
	  @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Label lbl2;

	    @FXML
	    private Label lbl0;

	    @FXML
	    private Label lbl1;

	    @FXML
	    private Label lbl3;

	    @FXML
	    private Label lbl4;

	    @FXML
	    private Label lbl5;

	    @FXML
	    private Label lbl6;

	    @FXML
	    private Label lbl7;

	    @FXML
	    private Label lbl8;

	    @FXML
	    private Label lbl9;

	    @FXML
	    private Label lbl10;

	    @FXML
	    private Label lbl11;

	    @FXML
	    private Label lbl12;

	    @FXML
	    private Label lbl13;

	    @FXML
	    private Label lbl14;

	    @FXML
	    private Label lbl15;

	    @FXML
	    private Button btnRandomRuzzle;

	    @FXML
	    private ListView<String> listView;
	@FXML
	void initialize() {
		System.out.println("1");
		   assert lbl2 != null : "fx:id=\"lbl2\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl0 != null : "fx:id=\"lbl0\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl1 != null : "fx:id=\"lbl1\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl3 != null : "fx:id=\"lbl3\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl4 != null : "fx:id=\"lbl4\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl5 != null : "fx:id=\"lbl5\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl6 != null : "fx:id=\"lbl6\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl7 != null : "fx:id=\"lbl7\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl8 != null : "fx:id=\"lbl8\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl9 != null : "fx:id=\"lbl9\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl10 != null : "fx:id=\"lbl10\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl11 != null : "fx:id=\"lbl11\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl12 != null : "fx:id=\"lbl12\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl13 != null : "fx:id=\"lbl13\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl14 != null : "fx:id=\"lbl14\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert lbl15 != null : "fx:id=\"lbl15\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert btnRandomRuzzle != null : "fx:id=\"btnRandomRuzzle\" was not injected: check your FXML file 'Ruzzle.fxml'.";
	        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		//aggiungo tutte le Label da lbl0 a lbl15 nella mia lista labels
		labels.add(lbl0);
		labels.add(lbl1);
		labels.add(lbl2);
		labels.add(lbl3);
		labels.add(lbl4);
		labels.add(lbl5);
		labels.add(lbl6);
		labels.add(lbl7);
		labels.add(lbl8);
		labels.add(lbl9);
		labels.add(lbl10);
		labels.add(lbl11);
		labels.add(lbl12);
		labels.add(lbl13);
		labels.add(lbl14);
		labels.add(lbl15);
		//setto inizialmente tutti col colore verde
		for (Label l : labels) 
			l.setStyle("-fx-background-color: green");
		
		//RICORDA: lo Style si setta così!!!
	}

	@FXML
	void doGenerateRandomRuzzle(ActionEvent event) {
		// Crea una matrice random per ruzzle. Nota che definisco matrix come variabile
		//locale perchè non mi serve che il controller la conosca al di fuori di questo 
		//metodo, lui non la usa mai: è il model che occorre che si memorizzi come è 
		//fatta tale matrice! Tale metodo poi la guarda giusto per poi settare la grafica 
		//basta! Non fare la stupidaggine di far generare tale matrice, siccome per la sua
		//generazione non ci vuole l'accesso ai dati, direttamente dal controller perchè è
		//è sbagliatissimo in quanto essa non servirà solo per la grafica ma anche per fare 
		//ricerche di parole in essa, per cui per forza è il modello ad occuparsene...Ecco 
		//perchè deve essere creata nel modello!
				char[][] matrix = model.generateRandomMatrix();

				// Update dell'interfaccia grafica
				for (int i = 0; i < model.getMatrixDim(); i++) {
					for (int j = 0; j < model.getMatrixDim(); j++) {
						labels.get(i * model.getMatrixDim() + j).setText(String.valueOf(matrix[i][j]).toUpperCase());
					}
				}

				// Reset colore label interfaccia grafica a verde
				for (Label l : labels)
					l.setStyle("-fx-background-color: green");//RICORDA: così si setta il colore!!!
				
				// Svuota la lista da visualizzare.
				listView.getItems().clear();

				model.solveRuzzle();

				// Ottieni la lista delle parole
				List<Parola> solutions = model.getSolutions();
				
				//????DA ORDINARE LA LISTA
				
				for (Parola el : solutions) {
					//RICORDA: ListView<String> devi vederla come un container di items 
					//(nel nostro caso String) disposti in verticale. Pertanto con getItems()
					//essa ti restituisce una lista di tutti gli items in essa contenuti.
					//ATT: è a tale lista che devi aggiungere la nuova soluzione, NON a 
					//ListView<>!!! Ricorda che ListView<> infatti nonè una List ma è un 
					//Container!!!
					listView.getItems().add(el.getNome());
				}

	}

	/*
	 * RICORDA: ho scelto come tipo OnMouseClicked perchè è quando clicco col mouse nella
	 * ListView che devo agire
	 */

    @FXML
    void doSearchRed(MouseEvent event) {
    	//RICORDA: con ListView devi prima di tutto chiamare il metodo listView.getSelectionModel() 
    	//e poi su di esso puoi chiamare, in base a cosa ti serve, i due utilissimi metodi
    	//getSelectedIndex() e getSelectedItem()
		int index = listView.getSelectionModel().getSelectedIndex();
		
		//controllo di aver selezionato una parola della lista e non uno spazio vuoto
		if (index >= 0) {

			Parola parola = model.getSolutions().get(index);
			List<Position> positions = parola.getPositions();

			//prima di tutto rimetto verde le caselle che al momento sono rosse a causa di
			//un'eventuale altra operazione fatta in precedenza
			for (Label l : labels)
				l.setStyle("-fx-background-color: green");

			for (Position p : positions) {
				//RICORDA X EXAM: data una matrice M e un vettore v contenenti gli stessi dati,
				//se il vettore lo riempio seguendo l'ordine per righe della matrice, allore
				//il passaggio da due indici ad un indice è il seguente, se consdero i valori
				//degli indici a partire da 0:
				//v[i*Num_column+j]=M[j][k]
				//Ciò perchè devo considerare che le caselle che ho fino alla mia, se i e j
				//sono indici che partono da 1, sono pari a (i-1)*Num_column+j. Siccome io 
				//qui parto con gli indici da 0, devo mettere al posto di i e j i+1 e j+1 e
				//poi devo sottrarre al tutto un 1 perchè anche l'indice del vettore parte da
				//0. Perciò ho: (i+1-1)*Num_column+(j+1)-1 e si ha così il risultato:
				//i*Num_column+j.
				int i=p.getX() * model.getMatrixDim() + p.getY();
				labels.get(i).setStyle("-fx-background-color: red");
			}

		
		} //se index <0 vuol dire che o la lista è vuota o ho selezionato io uno spazio vuoto
		  //in entrambi i casi non devo fare nulla 
    }

	
}
