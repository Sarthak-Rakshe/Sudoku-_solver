//package org.example;
//****************************************************** SIMPLE BACKTRACKING APPROACH ***********************
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.geom.RoundRectangle2D;
//
//class RoundedButton extends JButton {
//    private final int radius;
//    private final int padding;
//
//    public RoundedButton(String text, int radius, int padding) {
//        super(text);
//        this.radius = radius;
//        this.padding = padding;
//        setContentAreaFilled(false);
//        setFocusPainted(false);
//        setBorderPainted(false);
//        setPreferredSize(new Dimension(100, 40));
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setColor(getBackground());
//        g2.fill(new RoundRectangle2D.Double(padding, padding, getWidth() - 2 * padding, getHeight() - 2 * padding, radius, radius));
//
//        super.paintComponent(g);
//    }
//}
//
//public class SudokuSolverGUI extends JFrame {
//    private static final int SIZE = 9;
//    private JTextField[][] cells = new JTextField[SIZE][SIZE];
//    private int[][] board = new int[SIZE][SIZE];
//
//    public SudokuSolverGUI() {
//        setTitle("Sudoku Solver");
//        setSize(400, 500);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        JPanel gridPanel = new JPanel();
//        gridPanel.setLayout(new GridLayout(3, 3));
//
//        Color[] boxColors = {
//                Color.BLACK, Color.BLACK, Color.BLACK,
//                Color.BLACK, Color.BLACK, Color.BLACK,
//                Color.BLACK, Color.BLACK,
//                Color.BLACK
//        };
//
//        for (int boxRow = 0; boxRow < 3; boxRow++) {
//            for (int boxCol = 0; boxCol < 3; boxCol++) {
//                JPanel boxPanel = new JPanel();
//                boxPanel.setLayout(new GridLayout(3, 3));
//                boxPanel.setBackground(boxColors[boxRow * 3 + boxCol]);
//
//                for (int row = boxRow * 3; row < boxRow * 3 + 3; row++) {
//                    for (int col = boxCol * 3; col < boxCol * 3 + 3; col++) {
//                        cells[row][col] = new JTextField();
//                        cells[row][col].setFont(new Font("Arial", Font.BOLD, 20));
//                        cells[row][col].setHorizontalAlignment(JTextField.CENTER);
//                        cells[row][col].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Thin border
//                        boxPanel.add(cells[row][col]);
//                    }
//                }
//                gridPanel.add(boxPanel);
//            }
//        }
//
//        JPanel buttonPanel = new JPanel();
//        JButton solveButton = new RoundedButton("Solve", 20, 10); // Radius 20, Padding 10
//        solveButton.setBackground(new Color(76, 175, 80));
//        solveButton.setForeground(Color.WHITE);
//        solveButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (getBoardFromInput()) {
//                    if (solveSudoku(board)) {
//                        setBoardToGUI();
//                    } else {
//                        JOptionPane.showMessageDialog(null, "No solution exists.");
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter numbers between 1 and 9.");
//                }
//            }
//        });
//
//        JButton clearButton = new RoundedButton("Clear", 20, 10); // Radius 20, Padding 10
//        clearButton.setBackground(new Color(255, 82, 82));
//        clearButton.setForeground(Color.WHITE);
//        clearButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                clearBoard();
//            }
//        });
//
//        buttonPanel.add(solveButton);
//        buttonPanel.add(clearButton);
//
//        add(gridPanel, BorderLayout.CENTER);
//        add(buttonPanel, BorderLayout.SOUTH);
//    }
//
//    private boolean getBoardFromInput() {
//        try {
//            for (int row = 0; row < SIZE; row++) {
//                for (int col = 0; col < SIZE; col++) {
//                    String text = cells[row][col].getText();
//                    if (text.isEmpty()) {
//                        board[row][col] = 0;
//                    } else {
//                        board[row][col] = Integer.parseInt(text);
//                        if (board[row][col] < 1 || board[row][col] > 9) {
//                            return false;
//                        }
//                    }
//                }
//            }
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
//
//    private void setBoardToGUI() {
//        for (int row = 0; row < SIZE; row++) {
//            for (int col = 0; col < SIZE; col++) {
//                cells[row][col].setText(board[row][col] == 0 ? "" : String.valueOf(board[row][col]));
//            }
//        }
//    }
//
//    private void clearBoard() {
//        for (int row = 0; row < SIZE; row++) {
//            for (int col = 0; col < SIZE; col++) {
//                cells[row][col].setText("");
//                board[row][col] = 0;
//            }
//        }
//    }
//
//    public static boolean solveSudoku(int[][] board) {
//        for (int row = 0; row < SIZE; row++) {
//            for (int col = 0; col < SIZE; col++) {
//                if (board[row][col] == 0) {
//                    for (int num = 1; num <= SIZE; num++) {
//                        if (isValid(board, row, col, num)) {
//                            board[row][col] = num;
//                            if (solveSudoku(board)) {
//                                return true;
//                            }
//                            board[row][col] = 0;
//                        }
//                    }
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    private static boolean isValid(int[][] board, int row, int col, int num) {
//        for (int i = 0; i < SIZE; i++) {
//            if (board[row][i] == num || board[i][col] == num) {
//                return false;
//            }
//        }
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (board[row - row % 3 + i][col - col % 3 + j] == num) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                SudokuSolverGUI solverGUI = new SudokuSolverGUI();
//                solverGUI.setVisible(true);
//            }
//        });
//    }
//}

//***************************** BITMASKING APPROACH ********************************************

package org.example;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SudokuSolverGUI extends JFrame {
    private static final int SIZE = 9;
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private int[][] board = new int[SIZE][SIZE];

    // Bitmask arrays for rows, columns, and boxes
    private int[] rowMask = new int[SIZE];
    private int[] colMask = new int[SIZE];
    private int[] boxMask = new int[SIZE];

    public SudokuSolverGUI() {
        setTitle("Sudoku Solver");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3)); // 3x3 grid for boxes

        Color[] boxColors = {
                Color.BLACK, Color.BLACK, Color.BLACK,
                Color.BLACK, Color.BLACK, Color.BLACK,
                Color.BLACK, Color.BLACK, Color.BLACK
        };

        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                JPanel boxPanel = new JPanel();
                boxPanel.setLayout(new GridLayout(3, 3)); // Each box is 3x3
                boxPanel.setBackground(boxColors[boxRow * 3 + boxCol]); // Set background color

                for (int row = boxRow * 3; row < boxRow * 3 + 3; row++) {
                    for (int col = boxCol * 3; col < boxCol * 3 + 3; col++) {
                        cells[row][col] = new JTextField();
                        cells[row][col].setFont(new Font("Arial", Font.BOLD, 20));
                        cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                        cells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                        boxPanel.add(cells[row][col]);
                    }
                }
                gridPanel.add(boxPanel);
            }
        }

        JPanel buttonPanel = new JPanel();
        JButton solveButton = new JButton("Solve");
        solveButton.setFont(new Font("Arial", Font.PLAIN, 20));
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getBoardFromInput()) {
                    initMasks(); // Initialize bitmasks from the input board
                    if (solveSudoku(board, 0, 0)) {
                        setBoardToGUI();
                    } else {
                        JOptionPane.showMessageDialog(null, "No solution exists.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input or not enough numbers. Please enter at least 17 numbers between 1 and 9.");
                }
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 20));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearBoard();
            }
        });

        JButton loadImageButton = new JButton("Load Image");
        loadImageButton.setFont(new Font("Arial", Font.PLAIN, 20));
        loadImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadImageAndExtractNumbers();
            }
        });

        buttonPanel.add(loadImageButton);
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);

        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadImageAndExtractNumbers() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            extractNumbersFromImage(selectedFile);
        }
    }

    private void extractNumbersFromImage(File imageFile) {
        ITesseract instance = new Tesseract();
        instance.setDatapath("C:\\Users\\Rakshe\\AppData\\Local\\Programs\\Tesseract-OCR\\tessdata\\"); // Set path
        // to tessdata folder
        instance.setLanguage("eng");

        try {
            String result = instance.doOCR(imageFile);
            parseOCRResult(result);
        } catch (TesseractException e) {
            JOptionPane.showMessageDialog(this, "Error reading image: " + e.getMessage());
        }
    }

    private void parseOCRResult(String result) {
        String[] lines = result.split("\n");
        int row = 0;
        for (String line : lines) {
            String[] numbers = line.trim().split("\\s+");
            for (int col = 0; col < numbers.length; col++) {
                if (row < SIZE && col < SIZE) {
                    try {
                        int number = Integer.parseInt(numbers[col]);
                        if (number >= 1 && number <= 9) {
                            board[row][col] = number;
                            cells[row][col].setText(String.valueOf(number));
                        }
                    } catch (NumberFormatException e) {
                        // Ignore non-numeric values
                    }
                }
            }
            row++;
        }
    }

    private boolean getBoardFromInput() {
        int filledCount = 0;
        try {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    String text = cells[row][col].getText();
                    if (text.isEmpty()) {
                        board[row][col] = 0;
                    } else {
                        board[row][col] = Integer.parseInt(text);
                        if (board[row][col] < 1 || board[row][col] > 9) {
                            return false;
                        }
                        filledCount++; // Count the filled cells
                    }
                }
            }
            // Ensure there are at least 17 filled cells
            if (filledCount < 17) {
                JOptionPane.showMessageDialog(null, "Please enter at least 17 numbers to solve.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void setBoardToGUI() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col].setText(board[row][col] == 0 ? "" : String.valueOf(board[row][col]));
            }
        }
    }

    private void clearBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col].setText("");
                board[row][col] = 0;
            }
        }
    }

    private void initMasks() {
        rowMask = new int[SIZE];
        colMask = new int[SIZE];
        boxMask = new int[SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int num = board[row][col];
                if (num != 0) {
                    int mask = 1 << num;
                    rowMask[row] |= mask;
                    colMask[col] |= mask;
                    boxMask[(row / 3) * 3 + col / 3] |= mask;
                }
            }
        }
    }

    private boolean solveSudoku(int[][] board, int row, int col) {
        if (row == SIZE) return true; // Solved the entire board

        int nextRow = (col == SIZE - 1) ? row + 1 : row;
        int nextCol = (col == SIZE - 1) ? 0 : col + 1;

        if (board[row][col] != 0) {
            return solveSudoku(board, nextRow, nextCol);
        }

        int boxIndex = (row / 3) * 3 + col / 3;
        for (int num = 1; num <= SIZE; num++) {
            int mask = 1 << num;
            if ((rowMask[row] & mask) == 0 && (colMask[col] & mask) == 0 && (boxMask[boxIndex] & mask) == 0) {
                // Place the number
                board[row][col] = num;
                rowMask[row] |= mask;
                colMask[col] |= mask;
                boxMask[boxIndex] |= mask;

                if (solveSudoku(board, nextRow, nextCol)) {
                    return true;
                }

                // Undo the placement (backtrack)
                board[row][col] = 0;
                rowMask[row] ^= mask;
                colMask[col] ^= mask;
                boxMask[boxIndex] ^= mask;
            }
        }

        return false; // No valid number found
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SudokuSolverGUI solverGUI = new SudokuSolverGUI();
                solverGUI.setVisible(true);
            }
        });
    }
}
