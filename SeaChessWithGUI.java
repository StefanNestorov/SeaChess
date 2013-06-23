
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.SwingConstants;


public class SeaChessWithGUI {

	private static JFrame frmSandChess;
	static private JTextField rowsTxtField;
	private JLabel lblEnterRows;
	private JLabel lblEnterCols;
	static private JTextField colsTxtField;
	private JButton btnCreateTable;
	static  JPanel panel;
	static JButton[][] buttonsMatrix;
	static int turnCounter = 0;
	static private JTextField sequenceLengthTxt;
	private JTextPane txtpnHowLongTo;
	private JLabel lblStefanNestorov;
	static int seqToWinGame;
	static String firstPlayerMark = "X";
	static String secondPlayerMark = "O";
	static JTextArea textGuide;
	static JLabel lblP1Result;
	static JLabel lblP2Result;
	static int player1Result = 0;
	static int player2Result = 0;
	static JPanel createHolder;
	static JButton newGameButton;
	

	public static void main(String[] args) {
		
					SeaChessWithGUI window = new SeaChessWithGUI();			
	}
	
	public SeaChessWithGUI() {
		buildGUI();
		SeaChessWithGUI.frmSandChess.setVisible(true);
	}

	static boolean isWinning(int counter){
		if(counter == seqToWinGame-1){
			return true;
		}else{
			return false;
		}
	}
	
	static boolean isInMatrix(int row, int col){
		if(row < buttonsMatrix.length && row >= 0 && col < buttonsMatrix[0].length && col >= 0){
			return true;
		}else{
			return false;
		}
	}

	public static void resetTable() {
		for (int i = 0; i < buttonsMatrix.length; i++) {
			for (int j = 0; j < buttonsMatrix[0].length; j++) {
				buttonsMatrix[i][j].setText("");
				buttonsMatrix[i][j].setEnabled(true);
			}
		}
	}
	
	static void checkUpDown(int row, int col, String c){
		int seqLengthCounter = 0;
		while(true){
			if(isInMatrix(row+1, col) && buttonsMatrix[row+1][col].getText().equals(c)){
				seqLengthCounter++;
				if(isWinning(seqLengthCounter)){
					playerWinUpdate();
					updateResult();
					resetTable();
					//Message for wining
					//isThereWinner = true;
					return;
				}
				row++;
				continue;
			}
			row-=seqLengthCounter;
			break;
		}
		while(true){
			if(isInMatrix(row-1, col) && buttonsMatrix[row-1][col].getText().equals(c)){
				seqLengthCounter++;
				if(isWinning(seqLengthCounter)){
					playerWinUpdate();
					updateResult();
					resetTable();
					//Message for wining
					//isThereWinner = true;
					return;
				}
				row--;
				continue;
			}
			break;
		}
	}

	static void checkLeftRight(int row, int col, String c){
		int seqLengthCounter = 0;
		while(true){
			if(isInMatrix(row, col-1) && buttonsMatrix[row][col-1].getText().equals(c)){
				seqLengthCounter++;
				if(isWinning(seqLengthCounter)){
					playerWinUpdate();
					updateResult();
					resetTable();
					return;
				}
				col--;
				continue;
			}
			col+=seqLengthCounter;
			break;
		}
		while(true){
			if(isInMatrix(row, col+1) && buttonsMatrix[row][col+1].getText().equals(c)){
				seqLengthCounter++;
				if(isWinning(seqLengthCounter)){
					playerWinUpdate();
					updateResult();
					resetTable();
					return;
				}
				col++;
				continue;
			}
			break;
		}
	}
	
	static void checkFirstDiagonal(int row, int col, String c){
		int seqLengthCounter = 0;
		while(true){
			if(isInMatrix(row-1, col-1) && buttonsMatrix[row-1][col-1].getText().equals(c)){
				seqLengthCounter++;
				if(isWinning(seqLengthCounter)){
					playerWinUpdate();
					updateResult();
					resetTable();
					return;
				}
				row--;
				col--;
				continue;
			}
			row+=seqLengthCounter;
			col+=seqLengthCounter;
			break;
		}
		while(true){
			if(isInMatrix(row+1, col+1) && buttonsMatrix[row+1][col+1].getText().equals(c)){
				seqLengthCounter++;
				if(isWinning(seqLengthCounter)){
					playerWinUpdate();
					updateResult();
					resetTable();
					return;
				}
				row++;
				col++;
				continue;
			}
			break;
		}
	}
	
	static void checkSecondDiagonal(int row, int col, String c){
		int seqLengthCounter = 0;
		while(true){
			if(isInMatrix(row-1, col+1) && buttonsMatrix[row-1][col+1].getText().equals(c)){
				seqLengthCounter++;
				if(isWinning(seqLengthCounter)){
					playerWinUpdate();
					updateResult();
					resetTable();
					return;
				}
				row--;
				col++;
				continue;
			}
			row+=seqLengthCounter;
			col-=seqLengthCounter;
			break;
		}
		while(true){
			if(isInMatrix(row+1, col-1) && buttonsMatrix[row+1][col-1].getText().equals(c)){
				seqLengthCounter++;
				if(isWinning(seqLengthCounter)){
					playerWinUpdate();
					updateResult();
					resetTable();
					return;
				}
				row++;
				col--;
				continue;
			}
			break;
		}
	}
	
	static void createTable(int rows, int cols){
		buttonsMatrix = new JButton[rows][cols];
		panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(10, 11, 365, 365);
		panel.setVisible(false);
		frmSandChess.getContentPane().add(panel);
		panel.setLayout(null);
		int x = 10;
		int y = 10;
		int width = 30;
		int height = 30;
		
	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) {
		final JButton button = new JButton();
		button.setSize(30, 30);
		Font font = new Font("Dialog", Font.PLAIN, 20);
		button.setFont(font);
		//button.setForeground(Color.black);
		button.setBackground(Color.yellow);
		button.setBorder(null);
		button.setBounds(x, y, width, height);
		buttonsMatrix[i][j] = button;
		panel.add(button);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				if(turnCounter %2 == 0){
				button.setText("X");
				}else {
					button.setText("O"); 
				}
				button.setEnabled(false);
		
				for (int k = 0; k < buttonsMatrix.length; k++) {
					for (int l = 0; l < buttonsMatrix[0].length; l++) {
						if(button.equals(buttonsMatrix[k][l])){	
							String markToCheck = "";
							if(turnCounter % 2 == 0){
								markToCheck = firstPlayerMark;
							}else{
								markToCheck = secondPlayerMark;
							}
							checkUpDown(k, l, markToCheck);
							checkLeftRight(k, l, markToCheck);
							checkFirstDiagonal(k, l, markToCheck);
							checkSecondDiagonal(k, l, markToCheck);
							if(isEvenResult()){
								resetTable();
								textGuide.setText("Even game");
							}
						}
					}
				}
				turnCounter++;
			}
			});
		x+=35;
		}
		x=10;
		y+=35;
	}
	panel.setVisible(true);
	}

	private void buildGUI() {
		frmSandChess = new JFrame();
		frmSandChess.setForeground(new Color(192, 192, 192));
		frmSandChess.setBackground(new Color(192, 192, 192));
		frmSandChess.getContentPane().setBackground(new Color(192, 192, 192));
		frmSandChess.setTitle("Sea Chess");
		frmSandChess.setBounds(100, 100, 634, 420);
		frmSandChess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSandChess.getContentPane().setLayout(null);
		
		textGuide = new JTextArea();
		textGuide.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		textGuide.setForeground(new Color(0, 0, 0));
		textGuide.setBackground(new Color(192, 192, 192));
		textGuide.setText("Table can have between"+"\n"+"3 and 10  rows and cols!");
		textGuide.setEditable(false);
		textGuide.setBounds(409, 108, 196, 42);
		frmSandChess.getContentPane().add(textGuide);
		
		btnCreateTable = new JButton("Start Game");
		btnCreateTable.setBackground(new Color(255, 255, 0));
		btnCreateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preventInputEmptyString();
				int rowsInput = Integer.parseInt(rowsTxtField.getText());
				int colsInput = Integer.parseInt(colsTxtField.getText());
				seqToWinGame = Integer.parseInt(sequenceLengthTxt.getText());
				if(rowsInput > 2 && rowsInput <= 10
						&& colsInput > 2 && colsInput <= 10){
					if(seqToWinGame > Math.max(rowsInput, colsInput) || seqToWinGame <3){
						textGuide.setText("Sequence required for"+"\n"+"win can be between 3 and  "+Math.max(rowsInput, colsInput));
						return;
					}
					
					createTable(Integer.parseInt(rowsTxtField.getText()), Integer.parseInt(colsTxtField.getText()));
					textGuide.setText("Good luck");
					btnCreateTable.setEnabled(false);
					createHolder.setVisible(false);
					newGameButton.setEnabled(true);
					return;
				}
				else{
					textGuide.setText("ERROR: rows and cols"+"\n"+"must be between 3 and 10!");
				}
			}
		});
		btnCreateTable.setBounds(409, 294, 108, 23);
		frmSandChess.getContentPane().add(btnCreateTable);
		
		lblStefanNestorov = new JLabel("\u00A9 Stefan Nestorov");
		lblStefanNestorov.setHorizontalAlignment(SwingConstants.CENTER);
		lblStefanNestorov.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblStefanNestorov.setBounds(502, 362, 114, 14);
		frmSandChess.getContentPane().add(lblStefanNestorov);
		
		newGameButton = new JButton("New Game");
		newGameButton.setEnabled(false);
		newGameButton.setBackground(new Color(255, 255, 0));
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			panel.setVisible(false);
//				resetTable();
				player1Result = 0;
				player2Result = 0;
				lblP1Result.setText("0");
				lblP2Result.setText("0");
				rowsTxtField.setText("0");
				colsTxtField.setText("0");
				sequenceLengthTxt.setText("0");
				textGuide.setText("Table can have between"+"\n"+ "3 and 10 rows and cols");
				btnCreateTable.setEnabled(true);
				createHolder.setVisible(true);		
				newGameButton.setEnabled(false);
			}
		});
		newGameButton.setBounds(409, 328, 108, 23);
		frmSandChess.getContentPane().add(newGameButton);
		
		JLabel lblScore = new JLabel("SCORE:");
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblScore.setBounds(474, 11, 46, 14);
		frmSandChess.getContentPane().add(lblScore);
		
		JLabel lblPlayer1 = new JLabel("Player 1 - X");
		lblPlayer1.setBounds(409, 36, 70, 17);
		frmSandChess.getContentPane().add(lblPlayer1);
		
		JLabel lblPlayer2 = new JLabel("Player 2 - O");
		lblPlayer2.setBounds(527, 36, 70, 17);
		frmSandChess.getContentPane().add(lblPlayer2);
		
		lblP1Result = new JLabel("0");
		lblP1Result.setHorizontalAlignment(SwingConstants.CENTER);
		lblP1Result.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblP1Result.setBounds(419, 62, 46, 35);
		frmSandChess.getContentPane().add(lblP1Result);
		
		lblP2Result = new JLabel("0");
		lblP2Result.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblP2Result.setHorizontalAlignment(SwingConstants.CENTER);
		lblP2Result.setBounds(537, 64, 46, 35);
		frmSandChess.getContentPane().add(lblP2Result);
		
		JLabel label_1 = new JLabel(":");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(489, 62, 22, 35);
		frmSandChess.getContentPane().add(label_1);
		
		createHolder = new JPanel();
		createHolder.setBackground(new Color(192, 192, 192));
		createHolder.setBounds(407, 161, 188, 122);
		frmSandChess.getContentPane().add(createHolder);
		createHolder.setLayout(null);
		
		lblEnterRows = new JLabel("Enter Rows");
		lblEnterRows.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterRows.setBounds(3, 11, 86, 17);
		createHolder.add(lblEnterRows);
		
		rowsTxtField = new JTextField();
		rowsTxtField.setBounds(134, 8, 44, 20);
		createHolder.add(rowsTxtField);
		rowsTxtField.setBackground(new Color(255, 255, 0));
		rowsTxtField.setText("0");
		rowsTxtField.setColumns(10);
		
		lblEnterCols = new JLabel("Enter Cols");
		lblEnterCols.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterCols.setBounds(3, 36, 86, 20);
		createHolder.add(lblEnterCols);
		
		colsTxtField = new JTextField();
		colsTxtField.setBounds(134, 36, 44, 20);
		createHolder.add(colsTxtField);
		colsTxtField.setBackground(new Color(255, 255, 0));
		colsTxtField.setText("0");
		colsTxtField.setColumns(10);
		
		txtpnHowLongTo = new JTextPane();
		txtpnHowLongTo.setBounds(0, 62, 122, 60);
		createHolder.add(txtpnHowLongTo);
		txtpnHowLongTo.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnHowLongTo.setBackground(new Color(192, 192, 192));
		txtpnHowLongTo.setEditable(false);
		txtpnHowLongTo.setText("How long to be the \r\nsequnce for winning \r\nthe game ?");
		
		sequenceLengthTxt = new JTextField();
		sequenceLengthTxt.setBounds(134, 67, 44, 20);
		createHolder.add(sequenceLengthTxt);
		sequenceLengthTxt.setBackground(new Color(255, 255, 0));
		sequenceLengthTxt.setText("0");
		sequenceLengthTxt.setColumns(10);
	}

	static void updateResult(){
		if(turnCounter % 2 == 0){
			player1Result++;
			lblP1Result.setText(Integer.toString(player1Result));
		}else{
			player2Result++;
			lblP2Result.setText(Integer.toString(player2Result));
		}
	}

	static boolean isEvenResult(){
		for (int i = 0; i < buttonsMatrix.length; i++) {
			for (int j = 0; j < buttonsMatrix[0].length; j++) {
				if(!buttonsMatrix[i][j].getText().equals("")){
					continue;
				}else{
					return false;
				}
				}
			}
		return true;
		}

	static void playerWinUpdate(){
		if(turnCounter % 2 == 0){
			textGuide.setText("Player 1 WIN ");
		}else{
			textGuide.setText("Player 2 WIN ");
		}
	}

	static void preventInputEmptyString(){
		if(rowsTxtField.getText().equals("")){
			rowsTxtField.setText("0");
		}
		if(colsTxtField.getText().equals("")){
			colsTxtField.setText("0");
		}
		if(sequenceLengthTxt.getText().equals("")){
			sequenceLengthTxt.setText("0");
		}
	}
}



