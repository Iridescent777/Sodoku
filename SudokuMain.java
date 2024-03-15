package sudoku;
import java.awt.*;
import java.awt.event.*;

//import javax.sound.sampled.Clip;
import javax.swing.*;


public class SudokuMain extends JFrame {

    private static final long serialVersionUID = 1L;
    private WelcomePage welcomePage;
    private MusicPlayer HomeMusic;

    private JMenuBar bar = new JMenuBar();
    private JMenu File = new JMenu("File");
    private JMenuItem NewGame = new JMenuItem("New Game");
    private JMenuItem ReSet = new JMenuItem("Reset Game");
    private JMenuItem Exit = new JMenuItem("Exit");

    private GamePageMain gamePage;

    public SudokuMain() {

        welcomePage = new WelcomePage();

        //==================== difficulty choosing ====================
        welcomePage.addDifficultyButtonsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                HomeMusic.stop();

                welcomePage.setVisible(false); // hide the welcome page
                remove(welcomePage);


                String get_command = e.getActionCommand();
                if (get_command.equals("Easy")) {
                    System.out.println("Easy button clicked(for debugging)");//360
                    gamePage = new GamePageMain(welcomePage, 20, 0, Double.POSITIVE_INFINITY); //easy game => 20 empty cells
                    add(gamePage);

                } else if (get_command.equals("Medium")) {
                    System.out.println("Medium button clicked(for debugging)");//330
                    gamePage = new GamePageMain(welcomePage, 50, 0, Double.POSITIVE_INFINITY); //medium game => 50 empty cells
                    add(gamePage);

                } else if (get_command.equals("Hard")) {
                    System.out.println("Hard button clicked(for debugging)");//480
                    gamePage = new GamePageMain(welcomePage, 75, 0, Double.POSITIVE_INFINITY); //hard game => 75 empty cells
                    add(gamePage);

                }

                remove(welcomePage);
                pack();
                revalidate();
                repaint();
            }
        });

        //===================================================================


        add(welcomePage);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sudoku");
        setVisible(true);

    }


    public class WelcomePage extends JFrame {

        private static final long serialVersionUID = 1L;
        private JButton easyButton;
        private JButton mediumButton;
        private JButton hardButton;

        public WelcomePage() {

            HomeMusic = new MusicPlayer("D:/temp/KaNong.wav"); //play the music
            HomeMusic.HomeMusicplay();


            setTitle("Sudoku Warriors");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 500);


            // Set up the components
            JLabel nameLabel = new JLabel("Sudoku Warriors");
            nameLabel.setFont(new Font("Lato", Font.BOLD, 46));
            nameLabel.setHorizontalAlignment(JLabel.CENTER);
            nameLabel.setForeground(Color.BLACK);


            JLabel quoteLabel = new JLabel("\"Try the Numbers Game, Minus the Math\"");
            quoteLabel.setFont(new Font("Bebas Neue", Font.PLAIN, 18));
            quoteLabel.setHorizontalAlignment(JLabel.CENTER);
            quoteLabel.setForeground(Color.BLACK);

            easyButton = new JButton("Easy");
            easyButton.setBackground(new Color(210, 180, 140));
            easyButton.setForeground(Color.BLACK);
            easyButton.setOpaque(true);
            easyButton.setFont(new Font("Arial", Font.BOLD, 24));
            easyButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            mediumButton = new JButton("Medium");
            mediumButton.setBackground(new Color(210, 180, 140)); // dark gray

            mediumButton.setOpaque(true);
            mediumButton.setFont(new Font("Arial", Font.BOLD, 24));
            mediumButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            hardButton = new JButton("Hard");
            hardButton.setPreferredSize(new Dimension(20, 20));
            hardButton.setBackground(new Color(210, 180, 140)); // dark gray
            hardButton.setOpaque(true);
            hardButton.setFont(new Font("Arial", Font.BOLD, 24));
            hardButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));


            // Add the components to the frame

            JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
            panel.setBackground(new Color(255, 235, 205));
            panel.setOpaque(true); // make the panel transparent
            panel.add(nameLabel);
            panel.add(quoteLabel);
            panel.add(easyButton);
            panel.add(mediumButton);
            panel.add(hardButton);
            panel.setBounds(0, 0, getWidth(), getHeight()); // position the panel

            // Add the background image and panel to the frame

            add(panel);

            // Show the frame
            setLayout(null); // use absolute positioning
            setVisible(true);


        }

        public void addDifficultyButtonsListener(ActionListener listener) {
            easyButton.addActionListener(listener);
            mediumButton.addActionListener(listener);
            hardButton.addActionListener(listener);
        }

    }


    public class GamePageMain extends JFrame {
        private static final long serialVersionUID = 1L;


        private GameBoardPanel board;
        private JButton btnHomePage = new JButton("Back To Home");
        //private SudokuMain sudokuMain;
        private WelcomePage welcomepage;

        int difficulty_reference_index = 0;
        int difficulty_reference_index_time = 0;
        double difficulty_reference_index_chances = 0;

        // Constructor
        public GamePageMain(SudokuMain.WelcomePage welcomepage_index, int difficulty_reference_index, int difficulty_reference_index_time, double difficulty_reference_index_chances) {

            this.difficulty_reference_index = difficulty_reference_index;
            this.difficulty_reference_index_chances = difficulty_reference_index_chances;
            this.difficulty_reference_index_time = difficulty_reference_index_time;
            bar.add(File);
            File.add(NewGame);
            File.add(ReSet);
            File.add(Exit);
            //setLocationRelativeTo(null);
            this.setWelcomepage(welcomepage_index);

            bar.setPreferredSize(new Dimension(200, 50));
            board = new GameBoardPanel(difficulty_reference_index_time, difficulty_reference_index_chances);

            JPanel main_Panel = new JPanel();
            main_Panel.setLayout(new BorderLayout());

            //======= top ========
            JPanel GB_Panel = new JPanel();
            main_Panel.add(GB_Panel, BorderLayout.NORTH);
            GB_Panel.add(board);


            //======= bottom =======
            JPanel bottom_panel = new JPanel();
            bottom_panel.add(btnHomePage);
            //bottom_panel.setBackground(Color.YELLOW);
            bottom_panel.add(bar);

            main_Panel.add(bottom_panel, BorderLayout.CENTER);

            HomeButtonListener listener = new HomeButtonListener();
            btnHomePage.addActionListener(listener);
            NewButtonListener listener1 = new NewButtonListener();
            NewGame.addActionListener(listener1);
            ResetButtonListener listener2 = new ResetButtonListener();
            ExitButtonListener listener3 = new ExitButtonListener();
            Exit.addActionListener(listener3);
            ReSet.addActionListener(listener2);
            btnHomePage.setFont(new Font("Times New Roman", Font.BOLD, 22));
            btnHomePage.setOpaque(true);
            btnHomePage.setBackground(new Color(210, 180, 140));
            btnHomePage.setForeground(Color.BLACK);
            btnHomePage.setPreferredSize(new Dimension(200, 50));
            btnHomePage.setBorder(BorderFactory.createLineBorder(new Color(150, 75, 0), 3));

            bar.setFont(new Font("Times New Roman", Font.BOLD, 22));
            UIManager.put("Menu.font", new Font("sans-serif", Font.PLAIN, 22));
            setJMenuBar(bar);
            bar.setOpaque(true);
            bar.setBackground(new Color(210, 180, 140));
            bar.setForeground(Color.BLACK);
            bar.setBorder(BorderFactory.createLineBorder(new Color(150, 75, 0), 3));
            board.newGame(difficulty_reference_index);


            add(main_Panel);
            pack();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Sudoku");
            setVisible(true);

        }

        //============== getters & setters ================
        public WelcomePage getWelcomepage() {
            return welcomepage;
        }


        public void setWelcomepage(WelcomePage welcomepage) {
            this.welcomepage = welcomepage;
        }
        //============================================


        private class HomeButtonListener implements ActionListener { //back to the home page

            public void actionPerformed(ActionEvent e) {


                dispose(); // dispose the GamePageMain instance (this)
                SudokuMain.this.pack();
                SudokuMain.this.revalidate();
                SudokuMain.this.repaint();

                //WelcomePage welcomePage = sudokuMain.getWelcomePage();
                // do something with the welcomePage instance
                welcomePage.setVisible(true);
                HomeMusic.HomeMusicplay();
                board.getCountdownClock().getGameOveralMusic().stop();
                board.getCountdownClock().getGameLastSecondsMusic().stop();

                board.getCountdownClock().setCountdownTime(-2);// try to delete everything after returning to the welcome page


            }

        }

        private class ResetButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                SudokuMain.this.pack();
                SudokuMain.this.revalidate();
                SudokuMain.this.repaint();
                for(int i = 0;i<GameBoardPanel.GRID_SIZE;i++){
                    for(int j = 0;j<GameBoardPanel.GRID_SIZE;j++){
                        if(board.cells[i][j].status!=CellStatus.GIVEN){
                            board.cells[i][j].status = CellStatus.TO_GUESS;
                            board.cells[i][j].setText("");
                            board.cells[i][j].setEditable(true);
                            board.cells[i][j].setBackground(Color.YELLOW);
                            board.cells[i][j].setForeground(Color.GRAY);
                        }
                    }
                }
            }
        }

        private class NewButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                SudokuMain.this.pack();
                SudokuMain.this.revalidate();
                SudokuMain.this.repaint();
                if (difficulty_reference_index == 20) {
                    GamePageMain gamePage1 = new GamePageMain(welcomePage, 20, 0, Double.POSITIVE_INFINITY); //easy game => 20 empty cells
                    SudokuMain.this.remove(gamePage);
                    gamePage = gamePage1;
                    add(gamePage);
                    board.newGame(difficulty_reference_index);
                } else if (difficulty_reference_index == 50) {
                    GamePageMain gamePage1 = new GamePageMain(welcomePage, 50, 0, Double.POSITIVE_INFINITY); //medium game => 50 empty cells
                    SudokuMain.this.remove(gamePage);
                    gamePage = gamePage1;
                    add(gamePage);
                    board.newGame(difficulty_reference_index);
                } else {
                    GamePageMain gamePage1 = new GamePageMain(welcomePage, 75, 0, Double.POSITIVE_INFINITY); //hard game => 75 empty cells
                    SudokuMain.this.remove(gamePage);
                    gamePage = gamePage1;
                    add(gamePage);
                    board.newGame(difficulty_reference_index);

                }
            }
        }

        private class ExitButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                System.exit(0); // optional - exit the application after showing the congratulatory message
            }
        }
    }





    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SudokuMain();
            }
        });
    }


}
