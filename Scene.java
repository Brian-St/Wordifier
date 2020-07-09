class Scene {

	private int Budget;
	private Role[] Roles;
	private String Name;
	private String Desc;
	private Boolean Used;
	private int Number;

	public Scene(int budget, int roleNum, String[] roleNames, String[] roleLines,  int[] roleLevels, int[] rolex, int[] roley, String name, String desc){
		Budget = budget;
		Name = name;
		Desc = desc;
		Used = false;
		Roles = new Role[roleNum];
		for(int i = 0 ; i < roleNum ; i++){
			Roles[i] = new Role(roleLevels[i], roleLines[i], roleNames[i], true, rolex[i], roley[i]);
		}
		Number = 10;
	}
	
	public void setRoleCoordinates(int x, int y) {
		for(Role role : Roles) {
			role.setx(x + role.getx());
			role.sety(y + role.gety());
		}
	}
	
	public int getBudget() {
		return Budget;
	}
	public Role getRole(int i) {
		return Roles[i];
	}
	public Role[] getRoles() {
		return Roles;
	}
	public int getRoleLength() {
		return Roles.length;
	}
	public String getName() {
		return Name;
	}
	public String getDesc() {
		return Desc;
	}
	public Boolean getUsed() {
		return Used;
	}
	public void setUsed(Boolean used) {
		Used = used;
	}
	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		Number = number;
	}
}
