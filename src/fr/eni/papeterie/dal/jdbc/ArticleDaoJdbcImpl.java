package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;

public class ArticleDaoJdbcImpl implements ArticleDAO {
	private static final String TYPE_STYLO = "TYPE_STYLO";
	private static final String TYPE_RAMETTE = "TYPE_RAMET";
	private static Connection con;

	// ajouter des article dans la table Article
	public void insert(Article article) throws DALException {

		PreparedStatement stmt = null;
		ResultSet idResult = null;

		try {
			// connection a la base de données
			con = JdbcTools.getConnection();

			// protection contre les requette SQL
			String sql = "insert into Articles (reference ,marque,designation,prixUnitaire,qteStock,grammage,couleur,type) values (?,?,?,?,?,?,?,?) ";

			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			//
			stmt.setString(1, article.getReference());
			stmt.setString(2, article.getMarque());
			stmt.setString(3, article.getDesignation());
			stmt.setFloat(4, article.getPrixUnitaire());
			stmt.setInt(5, article.getQteStock());

			if (article instanceof Ramette) {
				Ramette r = (Ramette) article;
				stmt.setString(8, TYPE_RAMETTE);
				stmt.setInt(6, r.getGrammage());
				stmt.setNull(7, Types.VARCHAR);
			}

			if (article instanceof Stylo) {
				Stylo s = (Stylo) article;
				stmt.setString(8, TYPE_STYLO);
				stmt.setNull(6, Types.INTEGER);
				stmt.setString(7, s.getCouleur());
			}

			int result = stmt.executeUpdate();

			if (result == 1) {
				idResult = stmt.getGeneratedKeys();
				if (idResult.next()) {
					article.setIdArticle(idResult.getInt(1));
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DALException("impossible d'ajouter l'article " + article, e);
		} finally {
			if (idResult != null) {
				try {
					idResult.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					JdbcTools.closeConnection();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public List<Article> selectAll() throws DALException {
		List<Article> listArticles = new ArrayList<Article>();

		Statement stmt = null;
		ResultSet rs = null;
		Article art;
		String SQL = "select idArticle, marque,reference,  designation,  prixUnitaire,  qteStock  from Articles ";

		try {
			con = JdbcTools.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				art = new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5),
						rs.getInt(6));

				listArticles.add(art);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DALException("liste d'article non trouvable", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					JdbcTools.closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return listArticles;

	}

	public Article selectById(int IdArticle) throws DALException {

		Connection con = null;
		PreparedStatement stmt = null;
		Article ar = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Articles where idArticle = ? ";

		try {
			con = JdbcTools.getConnection();

			stmt = con.prepareStatement(sql);
			stmt.setInt(1, IdArticle);
			rs = stmt.executeQuery();

			if (rs.next()) {
				if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())) {

					Stylo art = new Stylo(rs.getInt("idArticle"), rs.getString("marque"),
							rs.getString("reference").trim(), rs.getString("designation"), rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"), rs.getString("couleur"));

					ar = (Article) art;

				}
				if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())) {

					Ramette art = new Ramette(rs.getInt("idArticle"), rs.getString("marque"),
							rs.getString("reference").trim(), rs.getString("designation"), rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"), rs.getInt("grammage"));
					ar = (Article) art;

				}
				if (ar == null) {
					System.out.println("Article aveec l'id: " + IdArticle + " n'est pas dans la base de données ");
					throw new DALException();

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DALException("article avec id= " + IdArticle + "non disponible", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				if (con != null) {
					try {
						JdbcTools.closeConnection();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			
		}
		return ar;

	}

	public void delete(int id) throws DALException {
		Connection con = null;
		PreparedStatement stmt = null;

		String sql = "DELETE FROM Articles where idArticle = ? ";

		try {
			con = JdbcTools.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			int rs = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DALException("impossible de supprimer l'article avec l'id  " + id, e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (con != null) {
					try {
						JdbcTools.closeConnection();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

	}

	public void update(Article a) throws DALException {

		Connection con = null;
		PreparedStatement stmt = null;

		String sql = "UPDATE Articles set reference=?, marque=?,designation=?,prixUnitaire=?,qteStock=?, grammage=?,couleur=? WHERE idArticle=?";

		try {
			con = JdbcTools.getConnection();
			stmt = con.prepareStatement(sql);

			stmt.setString(1, a.getReference());
			stmt.setString(2, a.getMarque());
			stmt.setString(3, a.getDesignation());
			stmt.setFloat(4, a.getPrixUnitaire());
			stmt.setInt(5, a.getQteStock());
			if (a instanceof Ramette) {
				Ramette r = (Ramette) a;
				stmt.setInt(6, r.getGrammage());
				stmt.setNull(7, Types.VARCHAR);
			}
			if (a instanceof Stylo) {
				Stylo s = (Stylo) a;
				stmt.setNull(6, Types.INTEGER);
				stmt.setString(7, s.getCouleur());
			}
			stmt.setInt(8, a.getIdArticle());
			int rs = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DALException("article non modifier ", e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (con != null) {
					try {
						JdbcTools.closeConnection();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}

	}
}
