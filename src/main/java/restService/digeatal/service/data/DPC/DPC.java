package restService.digeatal.service.data.DPC;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.ex.ODataException;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.uri.UriParameter;

import restService.digeatal.service.data.DB.DBConnector;
import restService.digeatal.service.data.DB.MySQL.MySqlConnector;
import restService.digeatal.service.metadata.MPC.MPC;
import restService.digeatal.service.util.Util;

public class DPC {

	DBConnector dbConnector;
	MPC modelProvider;
	Entity entity = null;

	public DPC(String tabName) throws Exception {
		dbConnector = new MySqlConnector(tabName);
	}

	public Entity getEntity(ODataRequest request, List<UriParameter> keyPredicates, EdmEntitySet edmEntitySet,
			EdmEntityType edmEntityType) throws ODataException {
//		EdmEntitySet startEdmEntitySet = uriResourceEntitySet.getEntitySet();
		EntityCollection entitySet;
		try {
			entitySet = getEntitySet(request, edmEntitySet);
		} catch (ClassNotFoundException | ODataException | SQLException e) {
			throw new ODataException(e.toString());
		}
		/* generic approach to find the requested entity */
		Entity requestedEntity = Util.findEntity(edmEntityType, entitySet, keyPredicates);

		if (requestedEntity == null) {
			throw new ODataApplicationException("Entity for requested key doesn't exist",
					HttpStatusCode.NOT_FOUND.getStatusCode(), Locale.ENGLISH);
		}
		return requestedEntity;
	}

	public EntityCollection getEntitySet(ODataRequest request, EdmEntitySet edmEntitySet) throws ODataException, SQLException, ClassNotFoundException{
		
		EntityCollection entityCollection = new EntityCollection();

		List<Entity> entityList = entityCollection.getEntities();

		ResultSet resultSet = dbConnector.readData();// new MySQLAccess(this.TABNAME).readDataBase();
		int x = 1;
		while (resultSet.next()) {
			entity = null;
			entity = new Entity();
			List<CsdlProperty> modelProperties = modelProvider.getEntityType().getProperties();
			for (CsdlProperty property : modelProperties) {
				Logger.getGlobal().log(Level.INFO, property.getType());
				if(property.getType() == EdmPrimitiveTypeKind.Int32.getFullQualifiedName().getName()) {
					entity.addProperty(new Property(null, property.getName(), ValueType.PRIMITIVE, resultSet.getInt(property.getName())));
				}else if(property.getType() == EdmPrimitiveTypeKind.String.getFullQualifiedName().getName()){
					entity.addProperty(new Property(null, property.getName(), ValueType.PRIMITIVE, resultSet.getString(property.getName())));			
				}
			}	
			entity.setId(createId(edmEntitySet.getName(), x));
			entityList.add(entity);
			x++;

		}

		return entityCollection;
	}

	public Entity create(ODataRequest request) throws ODataException {
		throw new ODataException("Method not implemented");
	}

	public Entity update(ODataRequest request) throws ODataException {
		throw new ODataException("Method not implemented");
	}

	public Entity delete(ODataRequest request) throws ODataException {
		throw new ODataException("Method not implemented");
	}

	protected URI createId(String entitySetName, Object id) {
		try {
			return new URI(entitySetName + "(" + String.valueOf(id) + ")");
		} catch (URISyntaxException e) {
			throw new ODataRuntimeException("Unable to create id for entity: " + entitySetName, e);
		}
	}

}
