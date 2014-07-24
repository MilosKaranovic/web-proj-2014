package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Data implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, User> korisnici;
	private HashMap<String, User> administratori;
	private HashMap<String, Kategorija> kategorije;
	private HashMap<String, Komponenta> komponente;
	private HashMap<String, Uredjaj> uredjaji;
	private ArrayList<Racun> racuni;
	
	public Data() {
		korisnici = new HashMap<String, User>();
		administratori = new HashMap<String, User>();
		komponente = new HashMap<String, Komponenta>();
		kategorije = new HashMap<String, Kategorija>();
		uredjaji = new HashMap<String, Uredjaj>();
		racuni = new ArrayList<Racun>();
		
		korisnici.put("2", new User("2","2","ime","prezime", "064/222551615", "mail@yahoo.com", "user"));
		korisnici.put("pera", new User("pera","pera","Petar","Petrovic", "064/4569875", "mail1@yahoo.com", "user"));
		
		administratori.put("1", new User("1","1","ime","prezime", "064/222551615", "mail@yahoo.com", "admin"));
		administratori.put("sima", new User("sima","sima","Sima","Simic", "064/22d51615", "mail@yahoo.com", "admin"));
		
		kategorije.put("peripheral", new Kategorija("peripheral", "mis, tastatura", ""));
		kategorije.put("keyboard", new Kategorija("keyboard", "tastature razne", "peripheral"));
		kategorije.put("mouse", new Kategorija("mouse", "misevi razni", "peripheral"));
		
		komponente.put("mouseR100", new Komponenta("mouseR100", 100.0, "10", "R100", "mouse", "http://www.google.com", "http://i.imgur.com/guMCFeF.jpg"));
		komponente.put("mouseTDI", new Komponenta("mouseTDI", 999.9, "2", "TDI", "mouse", "http://www.google.com", "http://i.imgur.com/bPr0qKTb.jpg"));
		komponente.put("keyboardA2", new Komponenta("keyboardA2", 1999.0, "2", "A2", "keyboard", "http://www.google.com", "http://i.imgur.com/bPr0qKTb.jpg"));
		
		ArrayList<String> listaKomponenti = new ArrayList<String>();
		listaKomponenti.add("mouseR100");
		listaKomponenti.add("keyboardA2");
		ArrayList<String> listaKomponenti1 = new ArrayList<String>();
		listaKomponenti1.add("mouseTDI");
		listaKomponenti1.add("mouseTDI");
		uredjaji.put("mouse and keyboard", new Uredjaj("mouse and keyboard", "mis i tastatura", listaKomponenti));
		uredjaji.put("mouse mouse", new Uredjaj("mouse mouse", "mis i mis", listaKomponenti1));
		
		ArrayList<String> k1 = new ArrayList<String>();
		k1.add("keyboardA2");
		k1.add("keyboardA2");
		Date d = new Date();
		d.setDate(26);
		Racun r1 = new Racun(k1, new ArrayList<String>(), 18, 1999.0+1999.0, d, "mica");
		racuni.add(r1);
	}
	
	public HashMap<String, User> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(HashMap<String, User> korisnici) {
		this.korisnici = korisnici;
	}

	public HashMap<String, User> getAdministratori() {
		return administratori;
	}

	public void setAdministratori(HashMap<String, User> administratori) {
		this.administratori = administratori;
	}

	public HashMap<String, Komponenta> getKomponente() {
		return komponente;
	}

	public void setKomponente(HashMap<String, Komponenta> komponente) {
		this.komponente = komponente;
	}

	public HashMap<String, Uredjaj> getUredjaji() {
		return uredjaji;
	}

	public void setUredjaji(HashMap<String, Uredjaj> uredjaji) {
		this.uredjaji = uredjaji;
	}

	public HashMap<String, Kategorija> getKategorije() {
		return kategorije;
	}

	public void setKategorije(HashMap<String, Kategorija> kategorije) {
		this.kategorije = kategorije;
	}

	public ArrayList<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(ArrayList<Racun> racuni) {
		this.racuni = racuni;
	}
	
	public void dodajRacun(Racun racun){
		this.racuni.add(racun);
	}



}
