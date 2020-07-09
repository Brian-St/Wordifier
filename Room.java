import java.util.Arrays;

class Room {
	private Scene scene;
	private int Shots;
	private String Name;
	private Role[] extras;
	private int[] CurrentPlayers;
	private String[] AdjacentRooms;
	private int x;
	private int y;
	private int[] shotx;
	private int[] shoty;
	private boolean visited;
	private int roomNum;
	private int shotMax;
	
	public Room(int shots, String name, int roleNum, String[] roleNames, int[] roleLevels, String[] roleLines, int[] rolex, int[] roley, String[] neighbors, int neighborNum, int x, int y, int[] shotx, int[] shoty, int num) {
		scene = null;
		Shots = shots;
		shotMax = Shots;
		Name = name;
		roomNum = num;
		CurrentPlayers = new int[Board.getPlayerNum()];
		Arrays.fill(CurrentPlayers, 8);
		extras = new Role[roleNum];
		AdjacentRooms = new String[neighborNum];
		for(int i = 0 ; i < roleNum ; i++){
			extras[i] = new Role(roleLevels[i], roleLines[i], roleNames[i], false, rolex[i], roley[i]);
		}
		for(int i = 0 ; i < neighborNum ; i++) {
			AdjacentRooms[i] = neighbors[i];
		}
		this.shotx = new int[shots];
		this.shoty = new int[shots];
		for(int i = 0 ; i < shots ; i++) {
			this.shotx[i] = shotx[i];
			this.shoty[i] = shoty[i];
		}
		this.x = x;
		this.y = y;
		visited = false;
	}
	
	public Room(String name, String[] neighbors, int neighborNum, int x, int y) {
		scene = null;
		Shots = 0;
		Name = name;
		CurrentPlayers = new int[Board.getPlayerNum()];
		extras = null;
		AdjacentRooms = new String[neighborNum];
		for(int i = 0 ; i < neighborNum ; i++) {
			AdjacentRooms[i] = neighbors[i];
		}
		this.x = x;
		this.y = y;
	}

	public void SceneWrap(){
		//removes rehearsal tokens
		for(Role role : extras) {
			if(role.getCurrentPlayer() != 8){
				Board.getPlayer(role.getCurrentPlayer()).resetRehearse();
				View.PlayerInfoRefresh(Board.getPlayer(role.getCurrentPlayer()));
			}
		}
		for(Role role : scene.getRoles()) {
			if(role.getCurrentPlayer() != 8){
				Board.getPlayer(role.getCurrentPlayer()).resetRehearse();
				View.PlayerInfoRefresh(Board.getPlayer(role.getCurrentPlayer()));
			}
		}
		Role[] roles = scene.getRoles();
		int[] rolls = new int[scene.getBudget()];
		for(int i = 0 ; i < rolls.length ; i++) {
			rolls[i] = Roll();
		}
		Arrays.sort(rolls);
		reverse(rolls);
		int j = roles.length-1;
		for(int i : rolls) {
			if(roles[j].getCurrentPlayer() != 8) {
				Player player = Board.getPlayer(roles[j].getCurrentPlayer());
				player.setMoney(player.getMoney()+i);
			}
			j--;
			if(j < 0)
				j = roles.length-1;
		}
		View.RemoveScene(scene.getNumber());
		emptyRoles();
		Board.decScene();
		scene = null;
	}
	
	public void reverse (int[] array) {
		  int n = array.length;
		  int middle = (int) Math.floor(n / 2);
		  int temp = 0;
		  for(int i = 0; i < middle; i += 1) {
		     temp = array[i];
		     array[i] = array[n - 1 - i];
		     array[n - 1 - i] = temp;
		  }
	}

	
	public void emptyRoom() {
		for(@SuppressWarnings("unused") int i : CurrentPlayers) {
			i = 0;
		}
	}
	
	public void emptyRoles() {
		for(Role role : extras) {
			role.setCurrentPlayer(8);
		}
		for(Role role : scene.getRoles()) {
			role.setCurrentPlayer(8);
		}
		for(int i : CurrentPlayers) {
			if(i!=8)
				Board.getPlayer(i).setCurrentRole(null);
		}
	}
	
	private static int Roll(){
	      return (int) (Math.random() * 6) + 1;
	}

	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public int getShots() {
		return Shots;
	}
	public void setShots(int shots) {
		Shots = shots;
	}
	public String getName() {
		return Name;
	}
	public Role getExtras(int i) {
		return extras[i];
	}
	public Role[] getExtras() {
		return extras;
	}
	public int[] getCurrentPlayers() {
		return CurrentPlayers;
	}
	public void setCurrentPlayers(int[] currentPlayers) {
		CurrentPlayers = currentPlayers;
	}
	public void setCurrentPlayer(int i) {
		CurrentPlayers[i] = i;
	}
	public String getAdjacentRooms(int i) {
		return AdjacentRooms[i];
	}
	public String[] getAdjacentRooms() {
		return AdjacentRooms;
	}
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	public int getRoleNum() {
		return extras.length;
	}
	public Role[] getRoles() {
		return extras;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public int getShotx(int i) {
		return shotx[i];
	}
	public int getShoty(int i) {
		return shoty[i];
	}
	public int getRoomNum() {
		return roomNum;
	}
	public int getShotMax() {
		return shotMax;
	}
}