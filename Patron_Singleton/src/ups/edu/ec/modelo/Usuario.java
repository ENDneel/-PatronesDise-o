package ups.edu.ec.modelo;

public class Usuario {
	private int id;
	private String name;
	private String user;
	private String password;
	
	
	
	
	
	public Usuario() {
		
	}



	public Usuario(String name, String user, String password) {
		
		this.name = name;
		this.user = user;
		this.password = password;
	}
	

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", user=" + user + ", password=" + password + "]";
	}
	
	
	
}
