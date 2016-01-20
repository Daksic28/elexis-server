package info.elexis.server.core.connector.elexis.services;

import ch.elexis.core.constants.StringConstants;
import info.elexis.server.core.connector.elexis.jpa.StoreToStringService;
import info.elexis.server.core.connector.elexis.jpa.model.annotated.AbstractDBObject;
import info.elexis.server.core.connector.elexis.jpa.model.annotated.Verrechnet;

public class VerrechnetService extends AbstractService<Verrechnet> {

	public static VerrechnetService INSTANCE = InstanceHolder.INSTANCE;

	private static final class InstanceHolder {
		static final VerrechnetService INSTANCE = new VerrechnetService();
	}

	private VerrechnetService() {
		super(Verrechnet.class);
	}

	public AbstractDBObject getVerrechenbar(Verrechnet vr) {
		String klasse = vr.getKlasse();
		String leistungenCode = vr.getLeistungenCode();

		return StoreToStringService.INSTANCE.createFromString(klasse + StringConstants.DOUBLECOLON + leistungenCode);
	}
}