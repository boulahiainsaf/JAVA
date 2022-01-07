package fr.eni.papeterie.dal;

import fr.eni.papeterie.dal.jdbc.ArticleDaoJdbcImpl;

public class Factory {
	public static ArticleDAO getArticleDAO() {
		return new ArticleDaoJdbcImpl();
	}

}
