import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Labyrinth extends JFrame implements MouseListener, KeyListener, ActionListener {
    JLabel label, labelTime, catLabel;
    int[][] labyrinth = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 3, 1, 3, 1},
            {1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 1, 0, 4, 0, 1, 0, 0, 0, 1, 0, 1},//3,3 1,6 8,3
            {1, 3, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 0, 1, 0, 0, 0, 1, 3, 0, 0, 1, 0, 0, 0, 1},
            {1, 3, 0, 1, 3, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 4, 1, 1, 1, 0, 1, 1, 0, 0, 4, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1},
            {1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1},
            {1, 1, 0, 1, 3, 0, 0, 1, 1, 3, 0, 0, 0, 1, 0, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 1, 3, 0, 0, 1, 1, 0, 0, 0, 3, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 4, 0, 1, 0, 1, 0, 1, 3, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 3, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 3, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 2},
            {1, 0, 0, 0, 0, 0, 1, 0, 3, 4, 1, 0, 3, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    ImageIcon characterRight = new ImageIcon("characterRight.png");
    ImageIcon characterLeft = new ImageIcon("characterLeft.png");
    ImageIcon characterUp = new ImageIcon("characterUp.png");
    ImageIcon characterDown = new ImageIcon("characterDown.png");
    ImageIcon cheeseIcon = new ImageIcon("cheese.png");
    ImageIcon catIcon = new ImageIcon("cat.png");
    ImageIcon trapIcon = new ImageIcon("mouse trap.png");
    ImageIcon heartIcon = new ImageIcon("heart.png");
    int y = characterDown.getIconHeight();
    int[] catY = {0, 0, 0, 0, -y, -y, 0, 0, 0, y, y};
    int x = characterDown.getIconWidth();
    int[] catX = {0, x, x, x, 0, 0, -x, -x, -x, 0, 0};
    boolean first = true;
    Color border = new Color(180, 105, 50);
    javax.swing.Timer timer;
    int seconds = 90;
    Point catLocation;
    int i = 0;
    int points = 0;
    int totalPoints = 15;
    int lives = 3;

    Labyrinth() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 850);
        this.setLayout(null);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setLocationRelativeTo(null);
        this.setTitle("Labyrinth Escape");
        // this.setBackground(border);
        //JPanel panel = new JPanel();
        //panel.setBounds(0,0,500,10);
        label = new JLabel();
        label.setBounds(x, y, x, y);
        label.setIcon(characterDown);
        //label.setBackground(Color.white);
        label.setOpaque(true);
        this.getContentPane().add(label);

        catLocation = new Point(x * 2, y * 14);
        catLabel = new JLabel();
        catLabel.setBackground(Color.white);
        catLabel.setBounds(x * 2, y * 14, x, y);
        catLabel.setOpaque(true);
        catLabel.setIcon(catIcon);
        //this.getContentPane().add(catLabel);

        labelTime = new JLabel();
        labelTime.setOpaque(true);
        labelTime.setBackground(border);
        //add(labelTime);
        labelTime.setBounds(10, 30, 150, 10);
        labelTime.setLayout(new BorderLayout());
        timer = new javax.swing.Timer(1000, this);
        timer.start();

        //this.add(panel);
        this.getContentPane().setBackground(Color.white);
        this.getContentPane().add(labelTime);

        this.setVisible(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            case 37 -> {

                if (labyrinth[(label.getX() - x) / x][(label.getY()) / y] == 3) {
                    points++;
                    labyrinth[(label.getX() - x) / x][(label.getY()) / y] = 0;
                }
                if (labyrinth[(label.getX() - x) / x][(label.getY()) / y] != 1) {
                    label.setLocation(label.getX() - x, label.getY());
                }

                if (labyrinth[(label.getX()) / x][(label.getY()) / y] == 4 || (label.getX() == catLocation.x && label.getY() == catLocation.y)) {
                    lives--;
                    labyrinth[(label.getX()) / x][(label.getY()) / y] = 0;
                    JOptionPane.showMessageDialog(null,
                            "Lives left: " + lives,
                            "You were caught!",
                            JOptionPane.INFORMATION_MESSAGE);
                    //label.setLocation(x,y);
                    repaint();
                    label.setLocation(label.getX(), label.getY());
                }

                label.setIcon(characterLeft);
                label.setOpaque(false);

            }
            case 38 -> {
                if (labyrinth[(label.getX()) / x][(label.getY() - y) / y] == 3) {
                    points++;
                    labyrinth[(label.getX()) / x][(label.getY() - y) / y] = 0;
                }
                if (labyrinth[(label.getX()) / x][(label.getY() - y) / y] != 1) {
                    label.setLocation(label.getX(), label.getY() - y);
                }

                if (labyrinth[(label.getX()) / x][(label.getY()) / y] == 4 || (label.getX() == catLocation.x && label.getY() == catLocation.y)) {
                    lives--;
                    labyrinth[(label.getX()) / x][(label.getY()) / y] = 0;
                    JOptionPane.showMessageDialog(null,
                            "Lives left: " + lives,
                            "You were caught!",
                            JOptionPane.INFORMATION_MESSAGE);
                    //label.setLocation(x,y);
                    repaint();
                    label.setLocation(label.getX(), label.getY());
                }

                label.setIcon(characterUp);
                label.setOpaque(false);
            }
            case 39 -> {

                if (labyrinth[(label.getX() + x) / x][(label.getY()) / y] == 3) {
                    points++;
                    labyrinth[(label.getX() + x) / x][(label.getY()) / y] = 0;
                }
                if (labyrinth[(label.getX() + x) / x][(label.getY()) / y] != 1) {
                    label.setLocation(label.getX() + x, label.getY());
                }
                if (labyrinth[(label.getX()) / x][(label.getY()) / y] == 4 || (label.getX() == catLocation.x && label.getY() == catLocation.y)) {
                    lives--;
                    labyrinth[(label.getX()) / x][(label.getY()) / y] = 0;
                    JOptionPane.showMessageDialog(null,
                            "Lives left: " + lives,
                            "You were caught!",
                            JOptionPane.INFORMATION_MESSAGE);
                    //label.setLocation(x,y);
                    repaint();
                    label.setLocation(label.getX(), label.getY());
                }

                label.setIcon(characterRight);
                label.setOpaque(false);
            }
            case 40 -> {

                if (labyrinth[(label.getX()) / x][(label.getY() + y) / y] == 3) {
                    points++;
                    labyrinth[(label.getX()) / x][(label.getY() + y) / y] = 0;
                }
                if (labyrinth[(label.getX()) / x][(label.getY() + y) / y] != 1) {
                    label.setLocation(label.getX(), label.getY() + y);
                }

                if (labyrinth[(label.getX()) / x][(label.getY()) / y] == 4 || (label.getX() == catLocation.x && label.getY() == catLocation.y)) {
                    lives--;
                    labyrinth[(label.getX()) / x][(label.getY()) / y] = 0;
                    JOptionPane.showMessageDialog(null,
                            "Lives left: " + lives,
                            "You were caught!",
                            JOptionPane.INFORMATION_MESSAGE);
                    //label.setLocation(x,y);
                    repaint();
                    label.setLocation(label.getX(), label.getY());
                }

                label.setIcon(characterDown);
                label.setOpaque(false);
            }
        }
    }

    public void updateLabel(int time) {
        labelTime.setText("Remaining time: " + time);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (labyrinth[(label.getX() + 8) / x][(label.getY() + 31) / y] == 2) {
            //System.out.println("Ai castigat!");
            JOptionPane.showMessageDialog(null,
                    "Congratulations! You won! \n Your points: " + points + '/' + totalPoints,
                    "End of game",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);

        }
    }

    @Override
    public void paint(Graphics g) {
        Color color = border;
        super.paint(g);
        g.translate(8, 31);
        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[0].length; j++) {

                switch (labyrinth[i][j]) {
                    //case 0, 2, 3, 4 -> color = Color.white;
                    case 1 -> color = border;
                    default -> color = Color.white;
                    //throw new IllegalStateException("Unexpected value: " + labyrinth[i][j]);
                }

                g.setColor(color);
                g.fillRect(x * i, y * j, x, y);

            }
        }
        if (label.getIcon() == characterDown){
            g.drawImage(characterDown.getImage(), label.getX(), label.getY(), null);
        } else if (label.getIcon() == characterUp) {
            g.drawImage(characterUp.getImage(), label.getX(), label.getY(), null);
        } else if (label.getIcon() == characterLeft) {
            g.drawImage(characterLeft.getImage(), label.getX(), label.getY(), null);
        } else if (label.getIcon() == characterRight) {
            g.drawImage(characterRight.getImage(), label.getX(), label.getY(), null);
        }
        //g.drawImage(label.getIm, label.getX(), label.getY(), null);
        g.drawImage(catIcon.getImage(), catLocation.x, catLocation.y, null);

        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[0].length; j++) {

                if (labyrinth[i][j] == 3) {
                    g.drawImage(cheeseIcon.getImage(), x * i, y * j, null);
                    if(first){

                    }
                } else if (labyrinth[i][j] == 4) {
                    g.drawImage(trapIcon.getImage(), x * i, y * j, null);
                }
            }
        }

        int livesLocation = 16;
        for(int i=0;i<lives;i++){
        g.drawImage(heartIcon.getImage(), x * livesLocation++, 0, null);}
        //g.drawImage(heartIcon.getImage(), x * 17, 0, null);
        //g.drawImage(heartIcon.getImage(), x * 18, 0, null);
        color = Color.black;
        g.setColor(color);
        g.setFont(new Font("TimesRoman",Font.PLAIN, 15));
        g.drawString("Remaining time: " + seconds, 10, 20);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getX()>label.getX()){

        if (labyrinth[(label.getX()+x) / x][(label.getY()  / y)] != 1) {
            label.setIcon(characterRight);
            label.setLocation(label.getX()+x, label.getY());
            //System.out.println("you pressed" +e.getX());
            if (labyrinth[(label.getX()) / x][(label.getY()  / y)] == 3) {
                points++;
                labyrinth[(label.getX()) / x][(label.getY()) / y] = 0;
            }
            if (labyrinth[(label.getX()) / x][(label.getY()) / y] == 4 || (label.getX() == catLocation.x && label.getY() == catLocation.y)) {
                lives--;
                labyrinth[(label.getX()) / x][(label.getY()) / y] = 0;
                JOptionPane.showMessageDialog(null,
                        "Lives left: " + lives,
                        "You were caught!",
                        JOptionPane.INFORMATION_MESSAGE);
                //label.setLocation(x,y);
                repaint();
                label.setLocation(label.getX(), label.getY());
            }


        }}else if (e.getX()<label.getX()){
            if (labyrinth[(label.getX()-x) / x][(label.getY()  / y)] != 1) {
                label.setIcon(characterLeft);
                label.setLocation(label.getX()-x, label.getY());
                //System.out.println("you pressed" +e.getX());
            }
            if (labyrinth[(label.getX()) / x][(label.getY()  / y)] == 3) {
                points++;
                labyrinth[(label.getX()) / x][(label.getY()) / y] = 0;
            }
            if (labyrinth[(label.getX()) / x][(label.getY()) / y] == 4 || (label.getX() == catLocation.x && label.getY() == catLocation.y)) {
                lives--;
                labyrinth[(label.getX()) / x][(label.getY()) / y] = 0;
                JOptionPane.showMessageDialog(null,
                        "Lives left: " + lives,
                        "You were caught!",
                        JOptionPane.INFORMATION_MESSAGE);
                //label.setLocation(x,y);
                repaint();
                label.setLocation(label.getX(), label.getY());
            }
        }
        if (e.getY()>label.getY()){
            if (labyrinth[(label.getX()) / x][((label.getY()+y)  / y)] != 1) {
                label.setIcon(characterDown);
                label.setLocation(label.getX(), label.getY()+y);
                //System.out.println("you pressed" +e.getX());
            }
            if (labyrinth[(label.getX()) / x][(label.getY()  / y)] == 3) {
                points++;
                labyrinth[(label.getX()) / x][(label.getY()) / y] = 0;
            }
            if (labyrinth[(label.getX()) / x][(label.getY()) / y] == 4 || (label.getX() == catLocation.x && label.getY() == catLocation.y)) {
                lives--;
                labyrinth[(label.getX()) / x][(label.getY()) / y] = 0;
                JOptionPane.showMessageDialog(null,
                        "Lives left: " + lives,
                        "You were caught!",
                        JOptionPane.INFORMATION_MESSAGE);
                //label.setLocation(x,y);
                repaint();
                label.setLocation(label.getX(), label.getY());
            }
        }else if (e.getY()<label.getY()){
            if (labyrinth[(label.getX()) / x][((label.getY()-y)  / y)] != 1) {
                label.setIcon(characterUp);
                label.setLocation(label.getX(), label.getY()-y);
                //System.out.println("you pressed" +e.getX());
            }
            if (labyrinth[(label.getX()) / x][(label.getY()  / y)] == 3) {
                points++;
                labyrinth[(label.getX()) / x][(label.getY()) / y] = 0;
            }
            if (labyrinth[(label.getX()) / x][(label.getY()) / y] == 4 || (label.getX() == catLocation.x && label.getY() == catLocation.y)) {
                lives--;
                labyrinth[(label.getX()) / x][(label.getY()) / y] = 0;
                JOptionPane.showMessageDialog(null,
                        "Lives left: " + lives,
                        "You were caught!",
                        JOptionPane.INFORMATION_MESSAGE);
                //label.setLocation(x,y);
                repaint();
                label.setLocation(label.getX(), label.getY());
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (seconds == 0 || lives == 0) {
            timer.stop();
            JOptionPane.showMessageDialog(null,
                    "You lost!\n Your points: " + points + '/' + totalPoints + "\nLives left: " + lives,
                    "End of game",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);

        } else {
            seconds--;
            //catLabel.setLocation(catLocation.x, catLocation.y);
            repaint();
            catLocation.x += catX[i];
            catLocation.y += catY[i];
            if (i < catX.length - 1) {
                i++;
            } else {
                i = 0;
            }

        }
       // labelTime.setLocation(10, 10);
        //labelTime.setText("Remaining time: " + seconds);
    }
}

