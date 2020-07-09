class Upgrade{
	public static void UpgradePlr(Player CurrentPlayer){
		if(CurrentPlayer.getLevel() == 6) {
			View.Displaymessage("You're already max level!");
			return;
		}
		int level = View.LevelOption(CurrentPlayer);
		if(CurrentPlayer.getLevel()>=level) {
			View.Displaymessage("You are already a higher level!");
			return;
		}
		String pay = View.CostOption();
		if (pay.equals("Money")) {
			   if (CostM(level) <= CurrentPlayer.getMoney()) {
				   CurrentPlayer.setLevel(level);
				   CurrentPlayer.setMoney(CurrentPlayer.getMoney()-CostM(level));
				   View.PlayerInfoRefresh(CurrentPlayer);
				   View.refreshPlayer(CurrentPlayer);
				   return;
			   } else {
				   View.Displaymessage("Not enough money!");
				   return;
			   }
		} else if (pay.equals("Fame")) {
		   		if (CostF(level) <= CurrentPlayer.getFame()) {
		   			CurrentPlayer.setLevel(level);
		   			CurrentPlayer.setFame(CurrentPlayer.getFame()-CostF(level));
		   			View.PlayerInfoRefresh(CurrentPlayer);
		   			View.refreshPlayer(CurrentPlayer);
		   			return;
		   		} else {
		   		   View.Displaymessage("Not enough fame!");
		   		   return;
		   		}
		} else if (pay.equals("Quit")) {
		   		return;
		}
     }	
	
	
   
   private static int CostM(int rank) {
	   return ((rank*rank)+(rank-2));
   }
   
   private static int CostF(int rank) {
	   return (rank-1)*5;
   }
}