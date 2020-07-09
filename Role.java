class Role{
	private int Level;
	private String Line;
	private String Name;
	private int CurrentPlayer;
	private Boolean Card;
	private int x;
	private int y;
	
	public Role(int lvl, String ln, String name, Boolean card, int x, int y) {
		Level = lvl;
		Line = ln;
		Name = name;
		Card = card;
		CurrentPlayer = 8;
		this.x = x;
		this.y = y;
	}

	public int rolesAvalible(Player Player) {
			if ( CurrentPlayer == 8 ){
				if ( Level > Player.getLevel() ){
					View.Displaymessage("Sorry your level is to low :( ");
					return 0;
				}
				View.Displaymessage("Role taken :) ");
				CurrentPlayer = Player.getPlayerNum();
				return 1;
			} else {
				View.Displaymessage("Sorry this role is taken :( ");
				return 0;
			}
	}

	public Boolean getCard() {
		return Card;
	}

	public int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		Level = level;
	}
	public String getLine() {
		return Line;
	}
	public String getName() {
		return Name;
	}
	public int getCurrentPlayer() {
		return CurrentPlayer;
	}
	public void setCurrentPlayer(int currentPlayer) {
		CurrentPlayer = currentPlayer;
	}
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	public void setx(int x) {
		 this.x = x;
	}
	public void sety(int y) {
		this.y = y;
	}
}