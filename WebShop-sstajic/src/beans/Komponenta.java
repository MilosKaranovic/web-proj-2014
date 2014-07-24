package beans;

public class Komponenta implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String naziv;
	private Double cena;
	private String kolicina;
	private String opis;
	private String kategorija;
	private String link;
	private String slika;
	
	public Komponenta(){
		
	}

	
	
	public Komponenta(String naziv, Double cena, String kolicina, String opis,
			String kategorija, String link, String slika) {
		super();
		this.naziv = naziv;
		this.cena = cena;
		this.kolicina = kolicina;
		this.opis = opis;
		this.kategorija = kategorija;
		this.link = link;
		this.slika = slika;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public String getKolicina() {
		return kolicina;
	}

	public void setKolicina(String kolicina) {
		this.kolicina = kolicina;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}
	
	
}
