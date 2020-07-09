class Turn{
	private static boolean TurnOver = false;
   
	public static void TakeTurn(Player CurrentPlayer) {
		BoardMouseListener.setCurrentPlayer(CurrentPlayer);
		View.CanAct(true);
		View.CanEndTurn(true);
		View.CanMove(false);
		View.CanRehearse(true);
		View.CanUpgrade(true);
		if(CurrentPlayer.getCurrentRole()== null) {
			View.CanAct(false);
			View.CanRehearse(false);
			View.CanMove(true);
		}
		if(!CurrentPlayer.getCurrentRoom().equals("office")) {
			View.CanUpgrade(false);
		}
		while(TurnOver == false) {
			System.out.print(""); //loop body needs something useless to continue running
		}
		TurnOver = false;
	}
	
	public static boolean isTurnOver() {
		return TurnOver;
	}
	public static void setTurnOver(boolean turnOver) {
		TurnOver = turnOver;
	}
}