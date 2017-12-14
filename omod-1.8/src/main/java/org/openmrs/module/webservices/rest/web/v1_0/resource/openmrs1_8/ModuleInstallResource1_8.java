/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs1_8;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;
import org.springframework.util.ResourceUtils;
import org.openmrs.module.ModuleUtil;
import org.openmrs.module.webservices.helper.ModuleActionInstall;
import org.openmrs.module.webservices.helper.ModuleFactoryWrapper;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.ConversionUtil;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.Creatable;
import org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.IllegalRequestException;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + "/moduleinstall", supportedClass = ModuleActionInstall.class, supportedOpenmrsVersions = {
        "1.10.*", "1.11.*", "1.12.*", "1.8.*", "1.9.*", "2.0.*", "2.1.*" })
public class ModuleInstallResource1_8 extends BaseDelegatingResource<ModuleActionInstall> implements Creatable {

	private ModuleFactoryWrapper moduleFactoryWrapper = new ModuleFactoryWrapper();

	public void setModuleFactoryWrapper(ModuleFactoryWrapper moduleFactoryWrapper) {
		this.moduleFactoryWrapper = moduleFactoryWrapper;
	}

	@Override
	public Object create(SimpleObject post, RequestContext context) throws ResponseException {
		moduleFactoryWrapper.checkPrivilege();
		ModuleActionInstall ModuleActionInstall = newDelegate();
		setConvertedProperties(ModuleActionInstall, post, getCreatableProperties(), true);

		String moduleuuid = ModuleActionInstall.getModuleuuid();
		String updateuri = ModuleActionInstall.getUpdateuri();
		URL downloadUrl = null;

		ServletContext servletContext = getServletContext(context);

		if (moduleuuid == null && updateuri == null) {
			throw new IllegalRequestException(
			        "ModuleUuid or updateuri not sent");
		}

		if (ResourceUtils.isUrl(updateuri)) {
			try {
				downloadUrl = new URL(updateuri);
			}
			catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		InputStream inputStream = ModuleUtil.getURLStream(downloadUrl);

		return ConversionUtil.convertToRepresentation(ModuleActionInstall, Representation.DEFAULT);
	}

	@Override
	public String getUri(Object instance) {
		return null;
	}

	@Override
	public ModuleActionInstall newDelegate() {
		return new ModuleActionInstall();
	}

	@Override
	public ModuleActionInstall save(ModuleActionInstall delegate) {
		throw new UnsupportedOperationException("ModuleActionInstall can not be saved");
	}

	@Override
	public ModuleActionInstall getByUniqueId(String uniqueId) {
		throw new UnsupportedOperationException("ModuleActionInstall can not be saved");
	}

	@Override
	protected void delete(ModuleActionInstall delegate, String reason, RequestContext context) throws ResponseException {
		throw new UnsupportedOperationException("ModuleActionInstall can not be deleted");
	}

	@Override
	public void purge(ModuleActionInstall delegate, RequestContext context) throws ResponseException {
		throw new UnsupportedOperationException("ModuleActionInstall can not be purged");
	}

	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("moduleuuid");
		description.addProperty("updateuri");
		return description;
	}

	@Override
	public DelegatingResourceDescription getCreatableProperties() throws ResourceDoesNotSupportOperationException {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("moduleuuid");
		description.addProperty("updateuri");
		return description;
	}

	private ServletContext getServletContext(RequestContext context) {
		return context.getRequest().getSession().getServletContext();
	}
}
