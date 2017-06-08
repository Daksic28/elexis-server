package info.elexis.server.core.connector.elexis.jpa.model.annotated;

import static info.elexis.server.core.connector.elexis.jpa.QueryConstants.*;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ch.elexis.core.constants.StringConstants;
import ch.elexis.core.model.IStockEntry;
import ch.elexis.core.model.article.IArticle;
import info.elexis.server.core.connector.elexis.jpa.ElexisTypeMap;
import info.elexis.server.core.connector.elexis.jpa.StoreToStringService;

@Entity
@Table(name = "STOCK_ENTRY")
@NamedQueries({
		@NamedQuery(name = QUERY_STOCK_ENTRY_findCummulatedStockSumOfArticle, query = "SELECT SUM(se.currentStock) FROM StockEntry se WHERE se.articleId = :"
				+ PARAM_ARTICLE_ID + " AND se.articleType = :" + PARAM_ARTICLE_TYPE + " AND se.deleted = false"),
		@NamedQuery(name = QUERY_STOCK_ENTRY_findCummulatedAvailabilityOfArticle, query = "SELECT MAX(CASE WHEN se.currentStock <= 0 THEN 0 WHEN "
				+ "(ABS(se.minimumStock)-se.currentStock) >= 0 THEN 1 ELSE 2 END) "
				+ "FROM StockEntry se  WHERE se.articleId = :" + PARAM_ARTICLE_ID + " AND se.articleType = :"
				+ PARAM_ARTICLE_TYPE + " AND se.deleted = false") })
public class StockEntry extends AbstractDBObjectIdDeleted implements IStockEntry {
	/**
	 * Stock this entry is shelved in
	 */
	@OneToOne
	@JoinColumn(name = "STOCK", insertable = true)
	Stock stock;

	@Column(name = "article_type", length = 255, nullable = false)
	String articleType;

	@Column(name = "article_id", length = 25, nullable = false)
	String articleId;

	/**
	 * The minimum number of items packages on stock. If lower than
	 * {@link #curr} we consider the stockage of this item to be critical.
	 */
	@Column(name = "min")
	int minimumStock;

	/**
	 * The current number of item packages on stock.
	 */
	@Column(name = "current")
	int currentStock;

	/**
	 * The maximum number of item packages targeted to be on stock. Acts as
	 * information value to dispatch re-orders.
	 */
	@Column(name = "max")
	int maximumStock;

	/**
	 * If there is an open package, this represents the number of fraction units
	 * available by this open package. The package itself is not part of current
	 * any more.
	 */
	@Column
	int fractionUnits;

	/**
	 * The contact this article may be purchased from.
	 */
	@OneToOne
	@JoinColumn(name = "PROVIDER", insertable = false)
	Kontakt provider;

	@Override
	public IArticle getArticle() {
		Optional<AbstractDBObjectIdDeleted> adbo = StoreToStringService.INSTANCE
				.createDetachedFromString(getArticleType() + StringConstants.DOUBLECOLON + getArticleId());
		if (adbo.isPresent()) {
			return (IArticle) adbo.get();
		}
		return null;
	}

	@Transient
	public void setArticle(AbstractDBObjectIdDeleted article) {
		String key = ElexisTypeMap.getKeyForObject(article);
		setArticleType(key);
		setArticleId(article.getId());
	}
	
	@Override
	public String getLabel() {
		return "";
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+ " articleId=["+getArticleId() + "]  min=["+getMinimumStock()+"] current=["+getCurrentStock()+"]";
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public Kontakt getProvider() {
		return provider;
	}

	@Override
	public void setProvider(Object provider) {
		this.provider = (Kontakt) provider;
	}

	@Override
	public int getCurrentStock() {
		return currentStock;
	}

	@Override
	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}

	@Override
	public int getMinimumStock() {
		return minimumStock;
	}

	@Override
	public void setMinimumStock(int minimumStock) {
		this.minimumStock = minimumStock;
	}

	@Override
	public int getMaximumStock() {
		return maximumStock;
	}

	@Override
	public void setMaximumStock(int maximumStock) {
		this.maximumStock = maximumStock;
	}

	public int getFractionUnits() {
		return fractionUnits;
	}

	public void setFractionUnits(int fractionUnits) {
		this.fractionUnits = fractionUnits;
	}
}
