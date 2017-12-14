/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.webservices.helper;

public class ModuleActionInstall {
	
	public ModuleActionInstall() {
	}
	
	public ModuleActionInstall(String moduleuuid, String updateuri) {
		this.moduleuuid = moduleuuid;
		this.updateuri = updateuri;
	}
	
	private String moduleuuid;
	
	private String updateuri;
	
	public void setModuleuuid(String moduleuuid) {
		this.moduleuuid = moduleuuid;
	}
	
	public String getModuleuuid() {
		return moduleuuid;
	}
	
	public void setUpdateuri(String updateuri) {
		this.updateuri = updateuri;
	}
	
	public String getUpdateuri() {
		return updateuri;
	}
	
}
