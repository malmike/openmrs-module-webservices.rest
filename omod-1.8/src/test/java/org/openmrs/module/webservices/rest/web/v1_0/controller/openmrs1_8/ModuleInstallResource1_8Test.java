/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.webservices.rest.web.v1_0.controller.openmrs1_8;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.Assert;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceControllerTest;

public class ModuleInstallResource1_8Test extends MainResourceControllerTest {
	
	@Test
	public void shouldReturnData() throws Exception {
		SimpleObject simpleObject = deserialize(handle(newPostRequest(getURI(),
		    "{\"updateuri\":\"https://dl.bintray.com/openmrs/omod/xforms-4.3.11.omod\", \"moduleuuid\":\"" + getUuid()
		            + "\"}")));
		Assert.assertEquals(simpleObject, null);
	}
	
	//ModuleUpdate resource does not support these operations
	@Override
	@Test(expected = Exception.class)
	public void shouldGetDefaultByUuid() throws Exception {
		super.shouldGetDefaultByUuid();
	}
	
	@Override
	@Test(expected = Exception.class)
	public void shouldGetRefByUuid() throws Exception {
		super.shouldGetRefByUuid();
	}
	
	@Override
	@Test(expected = Exception.class)
	public void shouldGetFullByUuid() throws Exception {
		super.shouldGetFullByUuid();
	}
	
	@Override
	@Test(expected = Exception.class)
	public void shouldGetAll() throws Exception {
		super.shouldGetAll();
	}
	
	@Override
	public String getURI() {
		return "moduleinstall";
	}
	
	@Override
	public String getUuid() {
		return null;
	}
	
	@Override
	public long getAllCount() {
		return 0;
	}
	
}
