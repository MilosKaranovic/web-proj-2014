package beans;

import java.util.ArrayList;

public class Uredjaj implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String naziv;
	private String opis;
	private ArrayList<String> komponente;
	
	public Uredjaj(){
	}

	public Uredjaj(String naziv, String opis, ArrayList<String> komponente) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.komponente = komponente;
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

	public ArrayList<String> getKomponente() {
		return komponente;
	}

	public void setKomponente(ArrayList<String> komponente) {
		this.komponente = komponente;
	}
	
	public void addKomponenta(String k){
		komponente.add(k);
	}
	
	public void remove(Komponenta k){
		for (String komp : komponente) {
			if(komp.equals(k.getNaziv())){
				komponente.remove(komp);
				return;
			}
		}
	}
	
	public boolean sadrziKomponentu(String k){
	
		for (String komponenta : komponente) {
			if(komponenta.equals(k)){
				return true;
			}
		}
		return false;
	}
}
