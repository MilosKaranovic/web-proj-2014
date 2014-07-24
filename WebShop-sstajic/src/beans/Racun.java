package beans;

import java.util.ArrayList;
import java.util.Date;

public class Racun implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> komponente;
	private ArrayList<String> uredjaji;
	private double porez;
	private double ukupnaCena;
	private Date datum;
	private String kupac;
	
	public Racun(){
		ukupnaCena = 0;
		porez = 18;
		komponente= new ArrayList<String>();
		uredjaji= new ArrayList<String>();
	}

	public Racun(ArrayList<String> komponente, ArrayList<String> uredjaji,
			double porez, double ukupnaCena, Date datum, String kupac) {
		super();
		this.komponente = komponente;
		this.uredjaji = uredjaji;
		this.porez = porez;
		this.ukupnaCena = ukupnaCena;
		this.datum = datum;
		this.kupac = kupac;
	}

	public ArrayList<String> getKomponente() {
		return komponente;
	}

	public void setKomponente(ArrayList<String> komponente) {
		this.komponente = komponente;
	}

	public ArrayList<String> getUredjaji() {
		return uredjaji;
	}

	public void setUredjaji(ArrayList<String> uredjaji) {
		this.uredjaji = uredjaji;
	}

	public double getPorez() {
		return porez;
	}

	public void setPorez(double porez) {
		this.porez = porez;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getKupac() {
		return kupac;
	}

	public void setKupac(String kupac) {
		this.kupac = kupac;
	}
	
	public void dodajKomponentu(String komponenta){
		komponente.add(komponenta);
	}
	
	public void ukloniKomponentu(String komponenta){
		for (String k : komponente) {
			if(k.equals(komponenta)){
				komponente.remove(k);
			}
		}
	}
	
	public void dodajUredjaj(String uredjaj){
		uredjaji.add(uredjaj);
	}
	
	public void ukloniUredjaj(String uredjaj){
		for (String k : uredjaji) {
			if(k.equals(uredjaj)){
				uredjaji.remove(k);
			}
		}
	}
}
