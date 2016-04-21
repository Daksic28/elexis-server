package info.elexis.server.core.connector.elexis.jpa.model.annotated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AT_MEDEVIT_MEDELEXIS_VAT_CH")
public class ObjVatInfo extends AbstractDBObjectIdDeleted {

	@Column(length = 80)
	private String objectId;
	
	@Column(length = 80)
	private String vatinfo;
	
	public String getObjectId() {
		return objectId;
	}
	
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
	public String getVatinfo() {
		return vatinfo;
	}
	
	public void setVatinfo(String vatinfo) {
		this.vatinfo = vatinfo;
	}
}
