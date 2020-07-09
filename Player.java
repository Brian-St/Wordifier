class Player{
   private int PlayerNum;
   private int Fame;
   private int Money;
   private String CurrentRoom;
   private Role CurrentRole;
   private int Rehearse;
   private int Level;
   private int Points;
   private String icon;
   
   public Player(int Num, int StartingFame, int StartingMoney, int StartingLevel, String icon) {
	   PlayerNum = Num;
	   Fame = StartingFame;
	   Money = StartingMoney;
	   Level = StartingLevel;
	   CurrentRoom = "trailer";
	   CurrentRole = null;
	   Rehearse = 0;
	   this.icon = "src/dice/" + icon + Level+".png";
	   genPoints();
   }
   
   public void genPoints() {
	   Points = Money + Fame + (5 * Level);
   }
   public int getPlayerNum() {
	   return PlayerNum;
   }
   public int getFame() {
	   return Fame;
   }
   public void setFame(int fame) {
	   Fame = fame;
	   genPoints();
   }
   public int getMoney() {
	   return Money;
   }
   public void setMoney(int money) {
	   Money = money;
	   genPoints();
   }
   public String getCurrentRoom() {
	   return CurrentRoom;
   }
   public void setCurrentRoom(String currentRoom) {
	   CurrentRoom = currentRoom;
   }
   public Role getCurrentRole() {
	   return CurrentRole;
   }
   public void setCurrentRole(Role currentRole) {
	   CurrentRole = currentRole;
   }
   public int getRehearse() {
	   return Rehearse;
   }
   public void resetRehearse() {
	   Rehearse = 0;
   }
   public void incRehearse() {
	   Rehearse++;
   }
   public int getLevel() {
	   return Level;
   }
   public void setLevel(int level) {
	   Level = level;
	   icon = icon.substring(0,10)+ Level + ".png";
	   genPoints();
   }
   public int getPoints() {
	   return Points;
   }
   public String getIcon() {
	   return icon;
   	}
}