package sudoku;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;





public class Main extends Application{
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Sudoku s = new Sudoku();
	
		GridPane grid = new GridPane();
		
		TextField[][] p = new TextField[9][9];
				
		for(int x = 0; x < p.length; x++) {
			for(int y = 0; y < p[x].length; y++){
				p[x][y] = new OneLetterTextField();
				p[x][y].setText(s.toString(x,y));
				p[x][y].setPrefColumnCount(1);	
				if(y < 3 || y > 5) {
					if(x < 3 || x > 5)
						p[x][y].setStyle("-fx-control-inner-background: #FFA500");
				}else if(x > 2 && x < 6)
					p[x][y].setStyle("-fx-control-inner-background: #FFA500");

				grid.add(p[x][y], x, y);
			}
		}		
		grid.setVgap(3);
		grid.setHgap(3);	
        grid.setAlignment(Pos.CENTER);
			    
	    Button solve = new Button("Solve");
	    Button clear = new Button("Clear");
	    
	    clear.setOnAction((e) -> {
	    	s.clear();

			for(int x = 0; x < p.length; x++) {
				for(int y = 0; y < p[x].length; y++){
					p[x][y].setText(s.toString(x, y));
				}
			}
	    });
	    
	    solve.setOnAction((e) -> {
	    	
	    	s.clear();
	    	for(int x = 0; x < p.length; x++) {
				for(int y = 0; y < p[x].length; y++){
					if(!p[x][y].getText().equals(""))
						s.set(x, y, Integer.valueOf(p[x][y].getText()));
				}
			}
	    	
	    	if(!s.solveSudoku()) {
	    		final Stage dialog = new Stage();
	            dialog.initOwner(primaryStage);
	            VBox dialogVbox = new VBox(20);
	            Scene dialogScene = new Scene(dialogVbox, 300, 50);
	            dialog.setScene(dialogScene);
	            dialogVbox.getChildren().add(new Text("Sudokut är olösbart min trevliga herre"));
	            dialog.show();
	    	}

	    		
			for(int x = 0; x < p.length; x++) {
				for(int y = 0; y < p[x].length; y++){
					p[x][y].setText(s.toString(x, y));
				}
			}
	    });

	    HBox hbox = new HBox(solve, clear);

	    
		BorderPane root = new BorderPane();

		root.setBottom(hbox);
		root.setTop(grid);

		Scene scene = new Scene(root, 260, 300);
		

		
		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}