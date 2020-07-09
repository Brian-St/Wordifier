class Work{
   public static void Rehearse(Player CurrentPlayer){
	     CurrentPlayer.incRehearse();
	     View.PlayerInfoRefresh(CurrentPlayer);
   }
   public static void Act(Player CurrentPlayer){
	   int roll = Roll();
	   Room room = Board.getRoom(CurrentPlayer.getCurrentRoom());
	   if(room.getScene().getBudget() < (roll +CurrentPlayer.getRehearse())) {
		   if(CurrentPlayer.getCurrentRole().getCard() == true){
			  CurrentPlayer.setFame(CurrentPlayer.getFame()+2);
			  View.Displaymessage("You Succeded and got two fame!");
		   } else {
			  CurrentPlayer.setFame(CurrentPlayer.getFame()+1);
			  CurrentPlayer.setMoney(CurrentPlayer.getMoney()+1);
			  View.Displaymessage("You Succeded and got 1 fame and 1 dollar!");
		   }
		   room.setShots(room.getShots()-1);
		   View.removeShot(room, room.getShots());
		   if(room.getShots() == 0) {
			   View.Displaymessage("Thats a wrap! :)");
			   room.SceneWrap();
		   }
	   } else {
         View.Displaymessage("You failed your roll :(");
		   if(CurrentPlayer.getCurrentRole().getCard() == false) {
			   View.Displaymessage("But you still get a dollar! :)");
			   CurrentPlayer.setMoney(CurrentPlayer.getMoney()+1);
		   }
	   }
	   View.PlayerInfoRefresh(CurrentPlayer);
   }

   private static int Roll(){
      return (int) (Math.random() * 6) + 1;
   }
}