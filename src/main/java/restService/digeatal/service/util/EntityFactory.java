package restService.digeatal.service.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.ex.ODataException;

import restService.digeatal.service.data.DPC.DPC;
import restService.digeatal.service.metadata.MetaDataProvider;
import restService.digeatal.service.metadata.MPC.MPC;
import restService.digeatal.service.metadata.MPC.Product_MPC;
import restService.digeatal.service.metadata.MPC.Restaurant_MPC;

public class EntityFactory {

	protected static ArrayList<CsdlEntityType> entityTypes = null;
	protected static ArrayList<CsdlEntitySet> entitySets = null;

	public static ArrayList<CsdlEntityType> getEntityTypes() throws ODataException {
		if (entityTypes == null) {

			entityTypes = new ArrayList<CsdlEntityType>();

			entityTypes.add(new Restaurant_MPC().getEntityType());
			entityTypes.add(new Product_MPC().getEntityType());
		}
		return entityTypes;
	}

	public static CsdlEntityContainer getEntityContainer() throws ODataException {

		// create EntityContainer
		CsdlEntityContainer entityContainer = new CsdlEntityContainer();
		entityContainer.setName(MetaDataProvider.CONTAINER_NAME);
		entityContainer.setEntitySets(getEntitySets());

		return entityContainer;

	}

	public static List<CsdlEntitySet> getEntitySets() throws ODataException {
		if (entitySets == null) {
			entitySets = new ArrayList<CsdlEntitySet>();
			entitySets.add(new Restaurant_MPC().getEntitySet());
			entitySets.add(new Product_MPC().getEntitySet());
		}

		return entitySets;
	}

	public static DPC getDataProvider(String entityType) throws ODataException {

		try {
			String className = "restService.digeatal.service.data.DPC." + entityType + "_DPC";
			Class<?> clazz;			
			clazz = Class.forName(className);
			java.lang.reflect.Constructor<?> constructor = clazz.getConstructors()[0];
			Object instance;			
			instance = constructor.newInstance();
			return (DPC) instance;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new ODataException("EntityType doesn't exist. (" + entityType + ")["+ e.toString() +"]");
		}

	}

	public static MPC getMetaDataProviderByEntityType(String entityType) throws ODataException {
		try {
			String className = "restService.digeatal.service.metadata.MPC." + entityType + "_MPC";
			Class<?> clazz = Class.forName(className);
			java.lang.reflect.Constructor<?> constructor = clazz.getConstructors()[0];
			Object instance = constructor.newInstance();

			return (MPC) instance;
		} catch (ClassNotFoundException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			throw new ODataException("EntityType doesn't exist.");
		}
	}

	public static MPC getMetaDataProviderByEntitySet(String entitySet) throws ODataException {
		String entityType = entitySet.substring(0, entitySet.length() - 1);
		return getMetaDataProviderByEntityType(entityType);
	}
}
