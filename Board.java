import org.w3c.dom.Document;

class Board{
   private static Room[] Rooms;
   private static Player[] Players;
   private static int SceneNum;
   private static int Day;
   private static int PlayerNum;
   private static Scene[] Scenes;
   private static String icons[] = {"b", "c", "g", "o", "p", "r", "v", "y"};
   
   public static void setUp(){
	   Document doc = null;
	   PlayerNum = 0;
	   while(PlayerNum == 0) {
		   PlayerNum = View.NumberOfPlayers();
	   }
	   Day = 4;
	   int startFame = 0;
	   int startLvl = 1;
	   if (PlayerNum < 4) {
		   Day = 3;
	   } else if(PlayerNum == 5) {
		   startFame = 2;
	   } else if(PlayerNum == 6) {
		   startFame = 4;
	   } else if(PlayerNum > 6) {
		   startLvl = 2;
	   }
	   Players = new Player[PlayerNum];
	   for(int i = 0; i < PlayerNum; i++) {
		   Players[i] = new Player(i, startFame, 0, startLvl, icons[i]);
	   }
	   for (Player p : Players) {
		   View.PlayerInfoSet(p);
	   }
	   try{
		   doc = Parse.getDocFromFile("src/board.xml");
		   Rooms = Parse.readRoomData(doc);
	   }catch (Exception e){
		   System.out.println("Error = "+e);
	   }
	   try{
		   doc = Parse.getDocFromFile("src/cards.xml");
		   Scenes = Parse.readSceneData(doc);
	   }catch (Exception e){
		   System.out.println("Error = "+e);
	   }
	   Room Trailer = getRoom("trailer");
	   for(int i = 0 ; i < PlayerNum ; i++) {
		   Trailer.setCurrentPlayer(i);
	   }
	   View.setUpPlayers(PlayerNum);
	   View.DayInfoSet(Day);
	   for(Room room : Rooms) {
		   View.addShots(room);
	   }
	   return;
   }
   public static void Game() {
	   int i = 0;
	   int end = 0;
	   while(end != 1) {
		   View.DisplayPlayer(i);
		   Turn.TakeTurn(Players[i]);
		   i++;
		   if(i == PlayerNum)
			   i = 0;
		   if(SceneNum == 1)
			   end = EndDay();
	   }
	   EndGame();
	   return;
   }
   public static void SetScenes(){
	   SceneNum = 10;
	   int j = 0;
	   Scene scene = null;
	   for(Room room : Rooms) {
		   if(room.getShots()!=0) {
			   while(scene == null) {
				   int i = (int) (Math.random() * 40);
				   if(Scenes[i].getUsed() == false) {
					   scene = Scenes[i];
					   scene.setUsed(true);
					   room.setScene(scene);
					   View.AddScene(i+1, room.getx(), room.gety(), j);
					   scene.setNumber(j);
					   scene.setRoleCoordinates(room.getx(), room.gety());
					   j++;
					   View.resetShots(room);
				   }
				   room.setVisited(false);
			   }
			   scene = null;
		   }
	   }
   }
   public static int EndDay(){
	   Day--;
	   View.DayInfoRefresh(Day);
	   if(Day == 0) {
		   return 1;
	   } else {
		   for(Room room: Rooms) {
			   room.emptyRoom();
			   room.setShots(room.getShotMax());
		   }
		   SetScenes();
		   Room Trailer = getRoom("trailer");
		   for(int i = 0 ; i < PlayerNum ; i++) {
			   Players[i].setCurrentRoom("trailer");
			   View.MovePlayer(Players[i].getPlayerNum(), Rooms[0]);
			   Trailer.setCurrentPlayer(i);
		   }
		   return 0;
	   }
   }
   public static void EndGame() {
	   int Winner = 0;
	   for(int i = 0 ; i < PlayerNum; i++) {
		   if(Players[i].getPoints() > Players[Winner].getPoints()) {
			   Winner = i;
		   }
	   }
	   View.Displaymessage("Player " + (Winner+1) + " Wins!");
	   return;
   }
   public static void printRooms() {
	   for(Room room : Rooms) {
		   System.out.print(room.getName() + " ");
	   }
	   System.out.println();
   }
   public static Room getRoom(String name) {
	   for(Room  room: Rooms) {
		   if(room.getName().equals(name))
			   return room;
	   }
	   return null;
   }
   public static void decScene() {
	   SceneNum--;
   }
   public static int getPlayerNum() {
	   return PlayerNum;
   }
   public static Player getPlayer(int i) {
	   return Players[i];
   }
}