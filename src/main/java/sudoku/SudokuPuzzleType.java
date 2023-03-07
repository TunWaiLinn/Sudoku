package sudoku;

public enum SudokuPuzzleType {

	EASY(9,9,3,3, 0.5, new String[] {"1","2","3","4","5","6","7","8","9"},"EASY"),
	MEDIUM(9,9,3,3, 0.4, new String[] {"1","2","3","4","5","6","7","8","9"},"MEDIUM"),
	HARD(9,9,3,3, 0.3, new String[] {"1","2","3","4","5","6","7","8","9"},"HARD"),
	EXPERT(9,9,3,3, 0.2222, new String[] {"1","2","3","4","5","6","7","8","9"},"EXPERT");
	private final int rows;
	private final int columns;
	private final int boxWidth;
	private final int boxHeight;

	private final double cluePercentage;
	private final String [] validValues;
	private final String desc;
	private SudokuPuzzleType(int rows, int columns, int boxWidth, int boxHeight, double cluePercentage, String [] validValues, String desc) {
		this.rows = rows;
		this.columns = columns;
		this.boxWidth = boxWidth;
		this.boxHeight = boxHeight;
		this.cluePercentage = cluePercentage;
		this.validValues = validValues;
		this.desc = desc;

	}

	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public int getBoxWidth() {
		return boxWidth;
	}
	
	public int getBoxHeight() {
		return boxHeight;
	}
	
	public String [] getValidValues() {
		return validValues;
	}
	
	public String toString() {
		return desc;
	}
	public double getCluePercentage() {
		return cluePercentage;
	}
}
