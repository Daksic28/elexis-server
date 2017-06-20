/*******************************************************************************
 * Copyright (c) 2015 MEDEVIT <office@medevit.at>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     MEDEVIT <office@medevit.at> - initial API and implementation
 ******************************************************************************/
package info.elexis.server.findings.fhir.jpa.model.annotated.listener;

import java.math.BigInteger;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import info.elexis.server.findings.fhir.jpa.model.annotated.AbstractDBObject;

public class AbstractDBObjectEntityListener {

	/**
	 * This method is called just before an update on the data set happens. We
	 * use it to always set the lastUpdate field to the correct value.
	 * 
	 * @param k
	 */
	@PreUpdate
	public void preUpdate(AbstractDBObject o) {
		o.setLastupdate(BigInteger.valueOf(System.currentTimeMillis()));
	}
	
	@PrePersist
	public void prePersist(AbstractDBObject o) {
		preUpdate(o);
	}
}
