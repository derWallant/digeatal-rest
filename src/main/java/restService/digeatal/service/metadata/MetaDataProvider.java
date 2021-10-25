package restService.digeatal.service.metadata;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;
import org.apache.olingo.commons.api.ex.ODataException;

import restService.digeatal.service.metadata.MPC.Restaurant_MPC;
import restService.digeatal.service.util.EntityFactory;

public class MetaDataProvider extends CsdlAbstractEdmProvider {

	// Service Namespace
	public static final String NAMESPACE = "OData.digeatal";

	// EDM Container
	public static final String CONTAINER_NAME = "Container";
	public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

	private CsdlEntitySet entitySet = null;
	private CsdlEntityType entityType = null;

	@Override
	public List<CsdlSchema> getSchemas() throws ODataException {

		CsdlSchema schema = new CsdlSchema();
		schema.setNamespace(NAMESPACE);
		schema.setEntityTypes(EntityFactory.getEntityTypes());

		// add EntityContainer
		schema.setEntityContainer(getEntityContainer());

		List<CsdlSchema> schemas = new ArrayList<CsdlSchema>();
		schemas.add(schema);

		return schemas;
	}

	@Override
	public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) throws ODataException {

		if (entityType == null) {
			try {
				entityType = EntityFactory.getMetaDataProviderByEntityType(entityTypeName.getName()).getEntityType();
			} catch (SecurityException | IllegalArgumentException | ODataException e) {
				throw new ODataException(e.toString());

			}
		}
		return entityType;

	}

	public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) throws ODataException {

		if (entitySet == null) {
			try {
				entitySet = EntityFactory.getMetaDataProviderByEntitySet(entitySetName).getEntitySet();
			} catch (SecurityException | IllegalArgumentException | ODataException e) {
				throw new ODataException(e.toString());

			}
		}
		return entitySet;

	}

	@Override
	public CsdlEntityContainer getEntityContainer() throws ODataException {
		return EntityFactory.getEntityContainer();
	}

	@Override
	public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) throws ODataException {
		// This method is invoked when displaying the Service Document at e.g.
		// http://localhost:8080/DemoService/DemoService.svc
		if (entityContainerName == null || entityContainerName.equals(CONTAINER)) {
			CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
			entityContainerInfo.setContainerName(CONTAINER);
			return entityContainerInfo;
		}

		return null;
	}

}
