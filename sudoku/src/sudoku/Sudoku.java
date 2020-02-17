package sudoku;

public class Sudoku {
	
	private int[][] game;
	private int[][] input;
	
	
	/**
	 * Skapar ett 9x9 sudoku.
	 */
	public Sudoku() {
		input = new int[9][9];
		game = new int[9][9];
	}
	/**
	 * Returnerar Sudokut som en 9x9 string.
	 * @return matris-återskapelse av en 9x9 string.
	 */
	public String print(){
		String ret = "";
		for(int x = 0; x < game.length; x++){
			for(int y = 0; y < game[x].length; y++)
				ret += game[y][x] + " ";
			ret += "\n";
		}
		return ret;
	}
	
	/**
	 * Metod som anropar vår recursive-algoritm.
	 * @return true/false beroende på om sudokut är löst eller inte.
	 */
	public boolean solveSudoku() {
		return solve(0,0,true);
	}
	
	private boolean solve(int x, int y, boolean dir) {
		if(x < 0) {
			if(y == 0)
				return false;
			y--;
			x = 8;
		}
		if(x > 8) {
			y++;
			x = 0;
		}
		if(y > 8)
			return true;
		
		if(input[x][y] == 0) {
			for(int i = game[x][y] + 1; i < 10; i++) {
				if(legal(x, y, i)) {
					game[x][y] = i;
					return solve(x + 1, y, true);
				}
			}
			game[x][y] = 0;
			return solve(x - 1, y, false);
		}else {
			if(legal(x, y, input[x][y]))
				return dir?solve(x + 1, y, true):solve(x - 1, y, false);
			else return false;
		}
	}
	
	
	
	private boolean legal(int x, int y, int a) {
		for(int i = 0; i < 9; i++) {
			if(i != y)
				if(game[x][i] == a) return false;
		}
		for(int i = 0; i < 9; i++) {
			if(i != x)
				if(game[i][y] == a) return false;
		}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if((x/3)*3 + i != x && (y/3)*3 + j != y)
					if(game[(x/3)*3 + i][(y/3)*3 + j] == a) return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Metod som hämtar värdet på rutan x, y som en string.
	 * @param x = kolumn-numret 
	 * @param y = rad-numret.
	 * @return string-återskapelse av siffran i ruta x,y.
	 */
	public String toString(int x, int y) {
		return game[x][y] > 0?String.valueOf(game[x][y]):"";
	}
	
	/**
	 * Metod som hämtar värdet vid x,y som en int.
	 * @param x = kolumn-numret
	 * @param y = rad-numret.
	 * @return string-återskapelse av siffran i ruta x,y.
	 */
	public int get(int x, int y) {
		return game[x][y];
	}
	
	/**
	 * Metod som sätter alla platser i sudokut till noll.
	 */
	public void clear() {
		for(int x = 0; x < game.length; x++) {
			for(int y = 0; y < game[x].length; y++) {
				game[x][y] = 0;
				input[x][y] = 0;
			}
		}
	}
	
	/**
	 * Metod som sätter in ett värde på en specifik plats i sudokut.
	 * @param x = kolumn-numret 
	 * @param y = rad-numret
	 * @param a = värdet som skall sättas in.
	 */
	public void set(int x, int y, int a) {
		if(a > -1 && a < 10) {
			game[x][y] = a;
			input[x][y] = a;
		}
	}
}
