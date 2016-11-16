package info.elexis.server.findings.fhir.jpa.codes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.elexis.core.findings.ICoding;
import ch.elexis.core.findings.codes.ICodingContribution;
import ch.elexis.core.findings.codes.ICodingService;
import ch.elexis.core.findings.codes.ILocalCodingContribution;

@Component
public class CodingService implements ICodingService {
	
	private List<ICodingContribution> contributions;
	
	private ILocalCodingContribution localCoding;
	
	private Logger getLogger(){
		return LoggerFactory.getLogger(CodingService.class);
	}
	
	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.STATIC, policyOption = ReferencePolicyOption.GREEDY)
	public synchronized void bindFhirTransformer(ICodingContribution contribution){
		if (contributions == null) {
			contributions = new ArrayList<ICodingContribution>();
		}
		if(contribution.getCodeSystem() != null && !contribution.getCodeSystem().isEmpty()) {
			contributions.add(contribution);
			if (contribution instanceof ILocalCodingContribution) {
				localCoding = (ILocalCodingContribution) contribution;
			}
		} else {
			getLogger().warn(
				"Contribution " + contribution + " returns no code system. It will be ignored.");
		}
	}
	
	public void unbindFhirTransformer(ICodingContribution contribution){
		if (contributions == null) {
			contributions = new ArrayList<ICodingContribution>();
		}
		contributions.remove(contribution);
	}
	
	@Override
	public List<String> getAvailableCodeSystems(){
		return contributions.stream().map(contribution -> contribution.getCodeSystem())
			.collect(Collectors.toList());
	}
	
	@Override
	public List<ICoding> getAvailableCodes(String system){
		if (contributions != null) {
			for (ICodingContribution iCodingContribution : contributions) {
				if (iCodingContribution.getCodeSystem().equals(system)) {
					return iCodingContribution.getCodes();
				}
			}
		}
		return Collections.emptyList();
	}
	
	@Override
	public void addLocalCoding(ICoding coding){
		if (localCoding != null) {
			localCoding.addCoding(coding);
		}
	}
	
	@Override
	public void removeLocalCoding(ICoding coding){
		if (localCoding != null) {
			localCoding.removeCoding(coding);
		}
	}
}
