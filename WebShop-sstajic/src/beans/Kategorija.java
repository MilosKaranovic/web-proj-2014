package beans;

public class Kategorija implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String naziv;
	private String opis;
	private String podkategorija;
	
	public Kategorija(){}

	public Kategorija(String naziv, String opis, String podkategorija) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.podkategorija = podkategorija;
	}
	
	public Kategorija(String naziv, String opis) {
		super();
		this.naziv = naziv;
		this.opis = opis;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getPodkategorija() {
		return podkategorija;
	}

	public void setPodkategorija(String podkategorija) {
		this.podkategorija = podkategorija;
	};
	
	
}
