import java.awt.event.*;

public class BoardMouseListener implements MouseListener{
	private static Player CurrentPlayer;
	
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource()== View.Act){
        	View.CanAct(false);
        	View.CanRehearse(false);
        	Work.Act(CurrentPlayer);
        	
        }
        else if (e.getSource()== View.Rehearse){
        	View.CanAct(false);
        	View.CanRehearse(false);
        	Work.Rehearse(CurrentPlayer);
        }
        else if (e.getSource()== View.Move){
        	View.CanMove(false);
        	Move.MoveTo(CurrentPlayer);
        } 
        else if (e.getSource()== View.Upgrade){
        	Upgrade.UpgradePlr(CurrentPlayer);
        }
        else if (e.getSource()== View.EndTurn){
            Turn.setTurnOver(true);
        }
     }
     public void mousePressed(MouseEvent e) {
     }
     public void mouseReleased(MouseEvent e) {
     }
     public void mouseEntered(MouseEvent e) {
     }
     public void mouseExited(MouseEvent e) {
     }
     
     public static void setCurrentPlayer(Player currentPlayer) {
    	 CurrentPlayer = currentPlayer;
     }
}
