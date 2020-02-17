package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.*;
import textproc.*;

public class BookReaderController extends Application{
	
	private ObservableList<Map.Entry<String, Integer>> words;
	private ListView<Map.Entry<String, Integer>> listView;

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void ReadFile() throws FileNotFoundException{
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		scan.useDelimiter(" ");
		Set<String> stopwords = new HashSet<String>();
		while (scan.hasNext()) {
			stopwords.add(scan.next());
		}
		scan.close();

		GeneralWordCounter a = new GeneralWordCounter(stopwords);

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			a.process(word);
		}

		s.close();
		words = FXCollections.observableArrayList(a.getWords());

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ReadFile();
		listView = new ListView<Entry<String, Integer>>(words);
		
	    HBox hbox = new HBox();
	    Button button1 = new Button("Alpha");
	    Button button2 = new Button("Freq");
	    HBox.setHgrow(button1, Priority.NEVER);
	    HBox.setHgrow(button2, Priority.NEVER);
	    button1.setMaxWidth(Double.MAX_VALUE);
	    button2.setMaxWidth(Double.MAX_VALUE);
	    hbox.getChildren().addAll(button1, button2);
	    
	    Button button3 = new Button("Find");
	    
	    button3.setDefaultButton(true);

	    HBox.setHgrow(button3, Priority.NEVER);
	    button3.setMaxWidth(Double.MAX_VALUE);
	     TextField field = new TextField();
	     HBox.setHgrow(field, Priority.ALWAYS);
	     hbox.getChildren().addAll(field, button3);
	     
	    button3.setOnAction((e) -> {
	    	for(Map.Entry<String, Integer> a: words) {
	    		if(a.getKey().toLowerCase().compareTo(field.getText().trim().toLowerCase()) == 0) {
	    			listView.scrollTo(a);
	    		}
	    	}
	    });
	    
	    
	    button1.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	words.sort((a,b) -> a.getKey().compareTo(b.getKey()));

	        }
	    });
	    
	    button2.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	FXCollections.sort(words, new WordCountComparator());
	        }
	    });
		
		BorderPane root = new BorderPane();
		root.setBottom(hbox);
		root.setCenter(listView);
		Scene scene = new Scene(root, 500, 500);
		
		primaryStage.setTitle("BookReader");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

}
