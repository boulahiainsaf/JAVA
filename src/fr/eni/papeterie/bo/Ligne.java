package fr.eni.papeterie.bo;

public class Ligne {

	
	private int qte;
	private Article article;

	
	
	public Ligne( Article article,int qte) {
		super();
		this.qte = qte;
		this.article = article;
	}
	public int getQte() {
		return qte;
	}
	public Article getArticle() {
		return article;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public float getPrix () {
		return this.getArticle().getPrixUnitaire();
	}
	@Override
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("Ligne [");
		buf.append(" qte=");
		buf.append(getQte());
		buf.append(", prix=");
		buf.append(getPrix());
		buf.append(", ");
		if (article != null) {
			buf.append("article=");
			buf.append(getArticle().toString());
		}
		buf.append("]");
		return buf.toString();
	}
}
