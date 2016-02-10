package info.elexis.server.core.connector.elexis.jpa.model.annotated;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AbstractDBObjectIdDeleted.class)
public class AbstractDBObjectIdDeleted_ {
	public static volatile SingularAttribute<AbstractDBObjectIdDeleted, String> id;
	public static volatile SingularAttribute<AbstractDBObjectIdDeleted, Boolean> deleted;
}
