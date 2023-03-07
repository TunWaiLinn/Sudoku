package sudoku;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class SudokuFrame extends JFrame implements ActionListener{

	private JPanel buttonSelectionPanel;
	private SudokuPanel sPanel;
	JButton solve = new JButton("Reveal answer");
	
	public SudokuFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Sudoku");
		this.setMinimumSize(new Dimension(800,700));
		
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Game");
		JMenu newGame = new JMenu("New Game");
		JMenuItem easy = new JMenuItem("Easy");
		easy.addActionListener(new NewGameListener(SudokuPuzzleType.EASY,30));
		JMenuItem medium = new JMenuItem("Medium");
		medium.addActionListener(new NewGameListener(SudokuPuzzleType.MEDIUM,30));
		JMenuItem hard = new JMenuItem("Hard");
		hard.addActionListener(new NewGameListener(SudokuPuzzleType.HARD,30));
		JMenuItem expert = new JMenuItem("Expert");
		expert.addActionListener(new NewGameListener(SudokuPuzzleType.EXPERT,30));

		newGame.add(easy);
		newGame.add(medium);
		newGame.add(hard);
		newGame.add(expert);

		file.add(newGame);
		menuBar.add(file);
		this.setJMenuBar(menuBar);
		
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setPreferredSize(new Dimension(800,600));
		
		buttonSelectionPanel = new JPanel();
		buttonSelectionPanel.setPreferredSize(new Dimension(200,500));
		Border padding = BorderFactory.createEmptyBorder(22, 0, 0, 0);
		buttonSelectionPanel.setBorder(padding);

		sPanel = new SudokuPanel();
		solve.addActionListener(this::actionPerformed);

		windowPanel.add(sPanel);
		windowPanel.add(buttonSelectionPanel);
		windowPanel.add(solve);
		this.add(windowPanel);
		
		rebuildInterface(SudokuPuzzleType.EASY, 26);


	}
	
	public void rebuildInterface(SudokuPuzzleType puzzleType,int fontSize) {
		SudokuPuzzle generatedPuzzle = new SudokuGenerator().generateRandomSudoku(puzzleType);
		sPanel.newSudokuPuzzle(generatedPuzzle);
		sPanel.setFontSize(fontSize);
		buttonSelectionPanel.removeAll();
		for(String value : generatedPuzzle.getValidValues()) {
			JButton b = new JButton();
			b.setText(value);
			b.setPreferredSize(new Dimension(50,50));
			b.addActionListener(sPanel.new NumActionListener());
			buttonSelectionPanel.add(b);
		}
		sPanel.repaint();
		buttonSelectionPanel.revalidate();
//		buttonSelectionPanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == solve) {
			SudokuGenerator.backtrackSudokuSolver(0,0,sPanel.getPuzzle());
		}
	}

	private class NewGameListener implements ActionListener {

		private SudokuPuzzleType puzzleType;
		private int fontSize;
		
		public NewGameListener(SudokuPuzzleType puzzleType,int fontSize) {
			this.puzzleType = puzzleType;
			this.fontSize = fontSize;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			rebuildInterface(puzzleType,fontSize);
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SudokuFrame frame = new SudokuFrame();
				frame.setVisible(true);
			}
		});
	}
}
