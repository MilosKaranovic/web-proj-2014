package beans;

import java.util.HashMap;

public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String ime;
	private String prezime;
	private String uloga;
	private String kontakt;
	private String mail;
	private boolean loggedIn;
	
	private Racun trenutniRacun;
	
	public User() {
		username = "";
		password = "";
		loggedIn = false;
		trenutniRacun= new Racun();
	}

	public User(String username, String password, String ime, String prezime, String kontakt, String mail, String uloga) {
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.uloga = uloga;
		this.kontakt = kontakt;
		this.mail = mail;
		loggedIn = false;
		trenutniRacun= new Racun();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Racun getTrenutniRacun() {
		return trenutniRacun;
	}

	public void setTrenutniRacun(Racun trenutniRacun) {
		this.trenutniRacun = trenutniRacun;
	}

	public boolean login(HashMap<String, User> data) {
		

			if (data.containsKey(this.username) && data.get(this.username).password.equals(password)) {
				this.username = data.get(this.username).username;
				this.password = data.get(this.username).password;
				this.ime = data.get(this.username).ime;
				this.prezime = data.get(this.username).prezime;
				this.uloga = data.get(this.username).uloga;
				this.kontakt = data.get(this.username).kontakt;
				this.mail = data.get(this.username).mail;
				loggedIn = true;
			} else {
				loggedIn = false;
			}

		
		
		return loggedIn;
	}

	public void logoff() {
		username = "";
		password = "";
		ime = "";
		prezime = "";
		uloga = "";
		kontakt = "";
		mail = "";
		this.loggedIn = false;
		trenutniRacun = new Racun();
	}
}
