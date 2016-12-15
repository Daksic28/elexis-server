package es.fhir.rest.core.model.util.transformer.helper;

import java.util.List;
import java.util.Optional;

import org.hl7.fhir.dstu3.model.Encounter;
import org.hl7.fhir.dstu3.model.Encounter.EncounterParticipantComponent;
import org.hl7.fhir.dstu3.model.Practitioner;
import org.hl7.fhir.dstu3.model.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.elexis.core.findings.IEncounter;
import ch.elexis.core.lock.types.LockInfo;
import ch.elexis.core.lock.types.LockResponse;
import ch.elexis.core.model.FallConstants;
import info.elexis.server.core.connector.elexis.jpa.model.annotated.AbstractDBObjectIdDeleted;
import info.elexis.server.core.connector.elexis.jpa.model.annotated.Behandlung;
import info.elexis.server.core.connector.elexis.jpa.model.annotated.Fall;
import info.elexis.server.core.connector.elexis.jpa.model.annotated.Kontakt;
import info.elexis.server.core.connector.elexis.locking.LockServiceInstance;
import info.elexis.server.core.connector.elexis.services.BehandlungService;
import info.elexis.server.core.connector.elexis.services.FallService;
import info.elexis.server.core.connector.elexis.services.KontaktService;

public class BehandlungHelper extends AbstractHelper {

	private static Logger logger = LoggerFactory.getLogger(AbstractHelper.class);

	public static Optional<Behandlung> createBehandlung(IEncounter iEncounter) {
		Optional<Behandlung> ret = getBehandlung(iEncounter);
		if (!ret.isPresent()) {
			Optional<Kontakt> patient = getPatient(iEncounter);
			Optional<Kontakt> serviceProvider = getPerformer(iEncounter);
			if (patient.isPresent()) {
				Behandlung behandlung = BehandlungService.INSTANCE.create();
				behandlung.setFall(getOrCreateDefaultFall(patient.get()));
				serviceProvider.ifPresent(sp -> behandlung.setMandant(sp));

				acquireAndReleaseLock(behandlung);
				
				ret = Optional.of(behandlung);
			}
		}
		return ret;
	}

	private static Fall getOrCreateDefaultFall(Kontakt kontakt) {
		List<Fall> faelle = KontaktService.getFaelle(kontakt);
		Fall defaultFall = lookUpDefaultFall(faelle);
		if (defaultFall == null) {
			defaultFall = createDefaultFall(kontakt);

			acquireAndReleaseLock(defaultFall);
		}
		return defaultFall;
	}

	private static void acquireAndReleaseLock(AbstractDBObjectIdDeleted dbObj) {
		Optional<LockInfo> lr = LockServiceInstance.INSTANCE.acquireLockBlocking(dbObj, 5);
		if (lr.isPresent()) {
			LockResponse lrs = LockServiceInstance.INSTANCE.releaseLock(lr.get());
			if (!lrs.isOk()) {
				logger.warn("Could not release lock for [{}] [{}]", dbObj.getClass().getName(), dbObj.getId());
			}
		}
	}

	private static Fall createDefaultFall(Kontakt kontakt) {
		return FallService.INSTANCE.create(kontakt, "online", FallConstants.TYPE_DISEASE, "KVG");
	}

	private static Fall lookUpDefaultFall(List<Fall> faelle) {
		if (faelle != null) {
			for (Fall fall : faelle) {
				if (fall.getBezeichnung().equals("online")) {
					return fall;
				}
			}
		}
		return null;
	}

	private static Optional<Behandlung> getBehandlung(IEncounter iEncounter) {
		String behandlungsId = iEncounter.getConsultationId();
		if (behandlungsId != null && !behandlungsId.isEmpty()) {
			return BehandlungService.INSTANCE.findById(behandlungsId);
		}
		return Optional.empty();
	}

	public static Optional<Kontakt> getPerformer(IEncounter iEncounter) {
		return KontaktService.INSTANCE.findById(iEncounter.getMandatorId());
	}

	public static Optional<Kontakt> getPatient(IEncounter iEncounter) {
		return KontaktService.INSTANCE.findById(iEncounter.getPatientId());
	}

	public static Optional<String> getMandatorId(Encounter fhirObject) {
		List<EncounterParticipantComponent> participants = fhirObject.getParticipant();
		for (EncounterParticipantComponent encounterParticipantComponent : participants) {
			if (encounterParticipantComponent.hasIndividual()) {
				Reference reference = encounterParticipantComponent.getIndividual();
				if (reference.getReferenceElement().getResourceType().equals(Practitioner.class.getSimpleName())) {
					return Optional.of(reference.getReferenceElement().getIdPart());
				}
			}
		}
		return Optional.empty();
	}

	public static Optional<String> getPatientId(Encounter fhirObject) {
		if (fhirObject.getPatient() != null && fhirObject.getPatient().hasReference()) {
			return Optional.of(fhirObject.getPatient().getReferenceElement().getIdPart());
		}
		return Optional.empty();
	}
}
