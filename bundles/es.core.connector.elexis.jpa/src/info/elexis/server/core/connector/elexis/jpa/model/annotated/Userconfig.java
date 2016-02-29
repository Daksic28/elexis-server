package info.elexis.server.core.connector.elexis.jpa.model.annotated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Entity
@Table(name = "userconfig")
@Cache(type=CacheType.NONE)
public class Userconfig extends AbstractDBObject {

	@OneToOne
	@JoinColumn(name = "UserID")
	private Kontakt owner;
	
	@Id
	@Column(unique = true, nullable = false, length = 80)
	private String param;
	
	@Lob
	private String value;

	public Kontakt getOwner() {
		return owner;
	}

	public void setOwner(Kontakt owner) {
		this.owner = owner;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
