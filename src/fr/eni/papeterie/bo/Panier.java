package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {
	
private float montant ;
public List<Ligne> lignesPanier;

public Panier() {
	lignesPanier =new ArrayList<Ligne>();
}

public float getMontant() {
	return this.montant;
}
public final Ligne getLigne(int index) {
	return lignesPanier.get(index);
}

 public final List<Ligne> getLignePanier(){
	 return this.lignesPanier;
 }
 public  void addLigne (Article article ,int qte) {
	 Ligne ar = new Ligne(article,qte);
	 lignesPanier.add(ar);
 }
 public void removeLigne(int index) {
	 lignesPanier.remove(index);
 }
 public void updateLigne(int index,int newQte) {
	 this.getLigne(index).setQte(newQte);
 }

@Override
public String toString() {
	StringBuffer bf =new StringBuffer();
	bf.append("Panier : \n\n");
	for (Ligne ligne : lignesPanier) {
		if (ligne != null){
			bf.append("ligne " + lignesPanier.indexOf(ligne) + " :\t");
			bf.append(ligne.toString());
			bf.append("\n");
		} else break;
	}
	bf.append("\nValeur du panier : " + getMontant());
	bf.append("\n\n");
	return bf.toString();
}
}
