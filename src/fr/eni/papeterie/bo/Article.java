package fr.eni.papeterie.bo;

public class Article {
	//les variables
 private Integer idArticle;
 private String reference;
 private String marque;
 private String designation;
 private float prixUnitaire;
 private int qteStock;
 
 

 //constructeur1 avec des parametre 
public Article(Integer idArticle,  String marque, String reference, String designation, float prixUnitaire,
		int qteStock) {
	super();
	this.idArticle = idArticle;
	this.reference = reference;
	this.marque = marque;
	this.designation = designation;
	this.prixUnitaire = prixUnitaire;
	this.qteStock = qteStock;
}
//constructeur sans paramettre 

public Article() {
	super();
}

//constructeur2 avec parametre 

public Article(String marque,String reference,  String designation, float prixUnitaire, int qteStock) {
	super();
	this.reference = reference;
	this.marque = marque;
	this.designation = designation;
	this.prixUnitaire = prixUnitaire;
	this.qteStock = qteStock;
}


//les getter et les setters
public Integer getIdArticle() {
	return this.idArticle;
}

public String getReference() {
	return this.reference;
}

public String getMarque() {
	return this.marque;
}

public String getDesignation() {
	return this.designation;
}

public float getPrixUnitaire() {
	return this.prixUnitaire;
}

public int getQteStock() {
	return this.qteStock;
}

public void setIdArticle(Integer idArticle) {
	this.idArticle = idArticle;
}

public void setReference(String reference) {
	this.reference = reference;
}

public void setMarque(String marque) {
	this.marque = marque;
}

public void setDesignation(String designation) {
	this.designation = designation;
}

public void setPrixUnitaire(float prixUnitaire) {
	this.prixUnitaire = prixUnitaire;
}

public void setQteStock(int qteStock) {
	this.qteStock = qteStock;
}
 //toString

@Override
public String toString() {
	return "Panier [idArticle=" + idArticle + ", reference=" + reference + ", marque=" + marque + ", designation="
			+ designation + ", prixUnitaire=" + prixUnitaire + ", qteStock=" + qteStock + "]";
}





}
