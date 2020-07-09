import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;

public class View extends JFrame{
	public static JLabel boardlabel;
	public static JLabel[] cardlabels = new JLabel[10];
	public static JLabel[] backlabels = new JLabel[10];
	public static JLabel[] playerlabels;
	public static JLabel[] playerlabelColors;
	public static JLabel[] iplayerlabels = new JLabel[8];
	public static JLabel leaderboard;
	public static JLabel day;
	
	public static JLabel[][] shotMarkers = new JLabel[10][3];
	
	public static int diceDimension = 40;
	
	public static JButton Act;
	public static JButton Rehearse;
	public static JButton Move;
	public static JButton Upgrade;
	public static JButton EndTurn;
	
	public static JLayeredPane BoardPane;
	
	public View() {
		super("Deadwood");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		BoardPane = getLayeredPane();
	    boardlabel = new JLabel();
	    ImageIcon icon =  new ImageIcon("src/board.jpg");
	    boardlabel.setIcon(icon); 
	    boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
	    BoardPane.add(boardlabel, new Integer(0));
	    setSize(icon.getIconWidth()+200,icon.getIconHeight()+140);
	    
	    leaderboard = new JLabel("Leaderboard");
	    leaderboard.setBounds(icon.getIconWidth()+40 , 30,100 ,20);
	    BoardPane.add(leaderboard,new Integer(2));
	    
	    Act = new JButton("ACT");
	    Act.setBackground(Color.white);
	    Act.setBounds(icon.getIconWidth()/5, icon.getIconHeight(),icon.getIconWidth()/5, 100);
	    Act.addMouseListener(new BoardMouseListener());
	       
	    Rehearse = new JButton("REHEARSE");
	    Rehearse.setBackground(Color.white);
	    Rehearse.setBounds((2*icon.getIconWidth())/5,icon.getIconHeight(),icon.getIconWidth()/5, 100);
	    Rehearse.addMouseListener(new BoardMouseListener());
	       
	    Move = new JButton("MOVE");
	    Move.setBackground(Color.white);
	    Move.setBounds(0,icon.getIconHeight(),icon.getIconWidth()/5, 100);
	    Move.addMouseListener(new BoardMouseListener());
	    
	    Upgrade = new JButton("UPGRADE");
	    Upgrade.setBackground(Color.white);
	    Upgrade.setBounds((3*icon.getIconWidth())/5,icon.getIconHeight(),icon.getIconWidth()/5, 100);
	    Upgrade.addMouseListener(new BoardMouseListener());
	    
	    EndTurn = new JButton("END TURN");
	    EndTurn.setBackground(Color.white);
	    EndTurn.setBounds((4*icon.getIconWidth())/5,icon.getIconHeight(),icon.getIconWidth()/5, 100);
	    EndTurn.addMouseListener(new BoardMouseListener());

	    BoardPane.add(Act, new Integer(2));
	    BoardPane.add(Rehearse, new Integer(2));
	    BoardPane.add(Move, new Integer(2));
	    BoardPane.add(Upgrade, new Integer(2));
	    BoardPane.add(EndTurn, new Integer(2));
	    
	    for(int i = 0; i < 10; i++) {
	    	cardlabels[i] = new JLabel();
	    }
	    for(int i = 0; i < 10; i++) {
	    	backlabels[i] = new JLabel();
	    }
	}
	
	public static void AddScene(int number, int x, int y, int i) {
		ImageIcon icon = new ImageIcon("src/cards/"+number+".png");
		cardlabels[i].setIcon(icon);
		cardlabels[i].setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
		cardlabels[i].setOpaque(true);
		icon = new ImageIcon("src/cards/cardback.png");
		backlabels[i].setIcon(icon);
		backlabels[i].setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
		backlabels[i].setOpaque(true);
		BoardPane.add(cardlabels[i], new Integer(1));
		BoardPane.add(backlabels[i], new Integer(2));
	}
	
	public static void addShots(Room room) {
		for(int i = 0; i < room.getShots(); i++) {
			shotMarkers[room.getRoomNum()][i] = new JLabel();
			ImageIcon icon = new ImageIcon("src/shot.png");
			shotMarkers[room.getRoomNum()][i].setIcon(icon);
			shotMarkers[room.getRoomNum()][i].setBounds(room.getShotx(i)+5, room.getShoty(i)+5, icon.getIconWidth(), icon.getIconHeight());
			shotMarkers[room.getRoomNum()][i].setOpaque(true);
			BoardPane.add(shotMarkers[room.getRoomNum()][i], new Integer(3));
		}
	}
	
	public static void resetShots(Room room) {
		for(int i = 0; i < room.getShots(); i++) {
			ImageIcon icon = new ImageIcon("src/shot.png");
			shotMarkers[room.getRoomNum()][i].setIcon(icon);
		}
		BoardPane.revalidate();
		BoardPane.repaint();
	}
	
	public static void removeShot(Room room, int num) {
		shotMarkers[room.getRoomNum()][num].setIcon(null);
		BoardPane.revalidate();
		BoardPane.repaint();
	}
	
	public static void RemoveScene(int number) {
		BoardPane.remove(cardlabels[number]);
		BoardPane.revalidate();
		BoardPane.repaint();
	}
	
	public static void RemoveBack(int number) {
		BoardPane.remove(backlabels[number]);
		BoardPane.revalidate();
		BoardPane.repaint();
	}
	
	public static void setUpPlayers(int pNum) {
		playerlabels = new JLabel[pNum];
		playerlabelColors = new JLabel[pNum];
		for(int i = 0; i < pNum ; i++) {
			ImageIcon icon = new ImageIcon(Board.getPlayer(i).getIcon());
			playerlabels[i]= new JLabel();
			playerlabelColors[i]= new JLabel();
			playerlabels[i].setIcon(icon);
			playerlabelColors[i].setIcon(icon);
			ImageIcon boardicon = new ImageIcon("src/board.jpg");
			playerlabelColors[i].setBounds(boardicon.getIconWidth()+120, 70+(100*i), 40, 40);
			if(i < 4) {
				playerlabels[i].setBounds(Board.getRoom("trailer").getx()+i*40+40, Board.getRoom("trailer").gety()+40,diceDimension, diceDimension);
			}else {
				playerlabels[i].setBounds(Board.getRoom("trailer").getx()+(i-4)*40+40, Board.getRoom("trailer").gety()+100,diceDimension, diceDimension);
			}
			playerlabels[i].setOpaque(true);
			playerlabelColors[i].setOpaque(true);
			BoardPane.add(playerlabels[i], new Integer(2));
			BoardPane.add(playerlabelColors[i], new Integer(2));
		}
	}
	
	public static void refreshPlayer(Player CurrentPlayer) {
		ImageIcon icon = new ImageIcon(CurrentPlayer.getIcon());
		playerlabels[CurrentPlayer.getPlayerNum()].setIcon(icon);
		playerlabelColors[CurrentPlayer.getPlayerNum()].setIcon(icon);
		BoardPane.revalidate();
		BoardPane.repaint();
	}
	
	public static String MoveOption(Room CurrentRoom) {
		String s = (String)JOptionPane.showInputDialog(BoardPane, "What Room would you like to go to?", "Move", JOptionPane.QUESTION_MESSAGE, null, CurrentRoom.getAdjacentRooms(), CurrentRoom.getAdjacentRooms(0));
		return s;
	}
	
	public static String RoleOption(Room CurrentRoom) {
		int button = JOptionPane.YES_NO_OPTION;
		int result = JOptionPane.showConfirmDialog(null,  "Would you like to take a role?", "Role", button);
		int i = 0;
		if(result == 0) {
			String[] options = new String[CurrentRoom.getRoleNum()+CurrentRoom.getScene().getRoleLength()];
			for(Role role : CurrentRoom.getRoles()) {
				options[i] = role.getName();
				i++;
			}
			for(Role role : CurrentRoom.getScene().getRoles()) {
				options[i] = role.getName();
				i++;
			}
			String s = (String)JOptionPane.showInputDialog(BoardPane, "What role would you like to take?", "Take Role", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			return s;
		} else {
			return null;
		}
	}
	
	public static void MovePlayer(int number, Room room) {
		playerlabels[number].setBounds(room.getx()+40*number, room.gety()+120, diceDimension, diceDimension);
	}
	
	public static void MovePlayer(int number, Role role) {
		playerlabels[number].setBounds(role.getx()+3, role.gety()+3, diceDimension, diceDimension);
	}
	
	public static int LevelOption(Player CurrentPlayer) {
		Object[] options = {"2", "3", "4", "5", "6"};
		String s = (String)JOptionPane.showInputDialog(BoardPane, "To what level would you like to upgrade too?", "Upgrade Player " + (CurrentPlayer.getPlayerNum()+1), JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return Integer.parseInt(s);
	}
	
	public static String CostOption() {
		Object[] options = {"Fame", "Money", "Quit"};
		String s = (String)JOptionPane.showInputDialog(BoardPane, "Would you like to use fame, money, or quit trying to upgrade?", "Fame or Money", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return s;
	}
	
	public static void PlayerInfoSet(Player CurrentPlayer) {
		ImageIcon icon =  new ImageIcon("src/board.jpg");
		String s = "<html>Player " + (CurrentPlayer.getPlayerNum()+1) + "<br/>Level: " + CurrentPlayer.getLevel() + "<br/>Money: " + CurrentPlayer.getMoney() + "<br/>Fame: " + CurrentPlayer.getFame() + "<br/>Points: " + CurrentPlayer.getPoints() + "<br/>Rehearse: " + CurrentPlayer.getRehearse() + "</html>";
		iplayerlabels[CurrentPlayer.getPlayerNum()] = new JLabel(s);
		iplayerlabels[CurrentPlayer.getPlayerNum()].setBounds(icon.getIconWidth()+40 ,50 + 100*CurrentPlayer.getPlayerNum(),100 ,100);
		BoardPane.add(iplayerlabels[CurrentPlayer.getPlayerNum()],new Integer(2));
	}
	
	public static void PlayerInfoRefresh(Player CurrentPlayer) {
		iplayerlabels[CurrentPlayer.getPlayerNum()].setText("<html>Player " + (CurrentPlayer.getPlayerNum()+1) + "<br/>Level: " + CurrentPlayer.getLevel() + "<br/>Money: " + CurrentPlayer.getMoney() + "<br/>Fame: " + CurrentPlayer.getFame() + "<br/>Points: " + CurrentPlayer.getPoints() + "<br/>Rehearse: " + CurrentPlayer.getRehearse() + "</html>");
	}
	
	public static void DayInfoSet(int days) {
		ImageIcon icon =  new ImageIcon("src/board.jpg");
		String s = "Days Left: "+days;
		day = new JLabel(s);
		day.setBounds(icon.getIconWidth()+40 ,10 ,100 ,20);
		BoardPane.add(day,new Integer(2));
	}
	
	public static void DayInfoRefresh(int days) {
		day.setText("Days Left: "+days);
	}
	
	public static void DisplayPlayer(int playerNum) {
		String message = "It's Player " + (playerNum+1) + "'s turn!";
		JOptionPane.showMessageDialog(BoardPane, message);
	}
	
	public static void Displaymessage(String message) {
		JOptionPane.showMessageDialog(BoardPane, message);
	}
	
	public static int NumberOfPlayers() {
		Object[] options = {"2", "3", "4", "5", "6", "7", "8"};
		String s = (String)JOptionPane.showInputDialog(BoardPane, "How many players are there today?", "Player Number", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return Integer.parseInt(s);
	}
	
	public static void CanAct(boolean t) {
		Act.setEnabled(t);
	}
	
	public static void CanRehearse(boolean t) {
		Rehearse.setEnabled(t);
	}
	
	public static void CanMove(boolean t) {
		Move.setEnabled(t);
	}
	
	public static void CanUpgrade(boolean t) {
		Upgrade.setEnabled(t);
	}
	
	public static void CanEndTurn(boolean t) {
		EndTurn.setEnabled(t);
	}
	
}
