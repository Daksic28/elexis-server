package info.elexis.server.core.connector.elexis.jpa.model.annotated.converter;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.elexis.server.core.connector.elexis.jpa.StoreToStringService;
import info.elexis.server.core.connector.elexis.jpa.model.annotated.AbstractDBObject;

public class ElexisDBStoreToStringConverter implements Converter {

	private static final long serialVersionUID = 7036321998248212269L;

	private Logger log = LoggerFactory.getLogger(ElexisDBStoreToStringConverter.class);

	@Override
	public Object convertObjectValueToDataValue(Object objectValue, Session session) {
		if(!(objectValue instanceof AbstractDBObject)) {
			log.warn(" {} is not an AbstractDBObject", objectValue.getClass());
			return null;
		}
		
		return StoreToStringService.INSTANCE.storeToString((AbstractDBObject) objectValue);
	}

	@Override
	public Object convertDataValueToObjectValue(Object dataValue, Session session) {
		return StoreToStringService.INSTANCE.createFromString((String) dataValue);
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public void initialize(DatabaseMapping mapping, Session session) {}


}