package fr.eni.papeterie.bo;

public class Stylo extends Article{

	private String couleur;
	
	
	public Stylo() {
		super();
	}


	public Stylo(Integer idArticle, String marque, String reference, String designation, float prixUnitaire,
			int qteStock, String couleur) {
		super(idArticle, marque, reference, designation, prixUnitaire, qteStock);
		this.couleur = couleur;
	}


	public Stylo(String marque, String reference, String designation, float prixUnitaire,
			int qteStock, String couleur) {
		super( marque, reference, designation, prixUnitaire, qteStock);
		this.couleur = couleur;
	}


	public String getCouleur() {
		return this.couleur;
	}


	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}


	@Override
	public String toString() {
		String s = String.format("%s Stylo [Couleur=%s]", super.toString(), getCouleur());

		return s;
	}




}
