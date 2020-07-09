class Move{
   public static void MoveTo(Player CurrentPlayer){
	   Room  room= Board.getRoom(CurrentPlayer.getCurrentRoom());
	   String newR = View.MoveOption(room);
	   Room newRoom = Board.getRoom(newR);
	   View.MovePlayer(CurrentPlayer.getPlayerNum(), newRoom);
	   CurrentPlayer.setCurrentRoom(newR);
	   newRoom.setCurrentPlayer(CurrentPlayer.getPlayerNum());
	   if(!newRoom.isVisited()) {
		   newRoom.setVisited(true);
		   View.RemoveBack(newRoom.getScene().getNumber());
	   }
	   for(;;) {
		   String s = View.RoleOption(newRoom);
		   if(s == null)
			   break;
		   boolean success = TakeRole(s, newRoom, CurrentPlayer);
		   if(success == true)
			   break;
	   }
   }
   
   public static boolean TakeRole(String rolename, Room CurrentRoom, Player CurrentPlayer){
	   	Role newRole = null;
		for(Role role : CurrentRoom.getRoles()) {
			if(role.getName().equals(rolename)){
				newRole = role;					
				break;
			}
		}
		for(Role role : CurrentRoom.getScene().getRoles()) {
			if(role.getName().equals(rolename)){
				newRole = role;
				break; 
			}
		}	
		if (newRole == null) { 
			return false;
		} else if(newRole.getCurrentPlayer() != 8) {
			View.Displaymessage("Someone is already working there!");
			return false;
		} else if(newRole.getLevel() > CurrentPlayer.getLevel()) {
			View.Displaymessage("You aren't high enough level!");
			return false;
		} else {
			CurrentPlayer.setCurrentRole(newRole);
			newRole.setCurrentPlayer(CurrentPlayer.getPlayerNum());
			View.MovePlayer(CurrentPlayer.getPlayerNum(), newRole);
			return true;
		}
   }
}