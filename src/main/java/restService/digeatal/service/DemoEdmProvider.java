package restService.digeatal.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlAction;
import org.apache.olingo.commons.api.edm.provider.CsdlActionImport;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlFunction;
import org.apache.olingo.commons.api.edm.provider.CsdlFunctionImport;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationPropertyBinding;
import org.apache.olingo.commons.api.edm.provider.CsdlParameter;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;
import org.apache.olingo.commons.api.edm.provider.CsdlReturnType;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;
import org.apache.olingo.commons.api.ex.ODataException;

public class DemoEdmProvider extends CsdlAbstractEdmProvider{

    // Service Namespace
    public static final String NAMESPACE = "OData.Demo";

    // EDM Container
    public static final String CONTAINER_NAME = "Container";
    public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

    // Entity Types Names
    public static final String ET_PRODUCT_NAME = "Product";
    public static final FullQualifiedName ET_PRODUCT_FQN = new FullQualifiedName(NAMESPACE, ET_PRODUCT_NAME);

    // Entity Set Names
    public static final String ES_PRODUCTS_NAME = "Products";

    public static final String ET_RESTAURANT_NAME = "Restaurant";
    public static final FullQualifiedName ET_RESTAURANT_FQN = new FullQualifiedName(NAMESPACE, ET_RESTAURANT_NAME);
    public static final String ES_RESTAURANT_NAME = "Restaurants";
    
    @Override 
    public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) {

        // this method is called for one of the EntityTypes that are configured in the Schema
        if(entityTypeName.equals(ET_PRODUCT_FQN)){

          //create EntityType properties
          CsdlProperty id = new CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
          CsdlProperty name = new CsdlProperty().setName("Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
          CsdlProperty  description = new CsdlProperty().setName("Description").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

          // create CsdlPropertyRef for Key element
          CsdlPropertyRef propertyRef = new CsdlPropertyRef();
          propertyRef.setName("ID");

          // configure EntityType
          CsdlEntityType entityType = new CsdlEntityType();
          entityType.setName(ET_PRODUCT_NAME);
          entityType.setProperties(Arrays.asList(id, name , description));
          entityType.setKey(Collections.singletonList(propertyRef));

          return entityType;
          
        } else if(entityTypeName.equals(ET_RESTAURANT_FQN)){
            //create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("Id").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty name = new CsdlProperty().setName("Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();
            propertyRef.setName("Id");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();
            entityType.setName(ET_RESTAURANT_NAME);
            entityType.setProperties(Arrays.asList(id, name));
            entityType.setKey(Collections.singletonList(propertyRef));

            return entityType;     	
        }

        return null;
      }
    
    @Override
    public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) {

        if(entityContainer.equals(CONTAINER)){
          if(entitySetName.equals(ES_PRODUCTS_NAME)){
            CsdlEntitySet entitySet = new CsdlEntitySet();
            entitySet.setName(ES_PRODUCTS_NAME);
            entitySet.setType(ET_PRODUCT_FQN);

            return entitySet;
          } else if(entitySetName.equals(ES_RESTAURANT_NAME)){
              CsdlEntitySet entitySet = new CsdlEntitySet();
              entitySet.setName(ES_RESTAURANT_NAME);
              entitySet.setType(ET_RESTAURANT_FQN);

              return entitySet;   	  
          }
        }

        return null;
      }

	@Override
    public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) {

        // This method is invoked when displaying the Service Document at e.g. http://localhost:8080/DemoService/DemoService.svc
        if (entityContainerName == null || entityContainerName.equals(CONTAINER)) {
            CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
            entityContainerInfo.setContainerName(CONTAINER);
            return entityContainerInfo;
        }

        return null;
    }

	@Override
    public List<CsdlSchema> getSchemas() {

	      // create Schema
	      CsdlSchema schema = new CsdlSchema();
	      schema.setNamespace(NAMESPACE);

	      // add EntityTypes
	      List<CsdlEntityType> entityTypes = new ArrayList<CsdlEntityType>();
	      entityTypes.add(getEntityType(ET_PRODUCT_FQN));
	      entityTypes.add(getEntityType(ET_RESTAURANT_FQN));
	      schema.setEntityTypes(entityTypes);

	      // add EntityContainer
	      schema.setEntityContainer(getEntityContainer());

	      // finally
	      List<CsdlSchema> schemas = new ArrayList<CsdlSchema>();
	      schemas.add(schema);

	      return schemas;
	    }

	@Override
    public CsdlEntityContainer getEntityContainer() {

	      // create EntitySets
	      List<CsdlEntitySet> entitySets = new ArrayList<CsdlEntitySet>();
	      entitySets.add(getEntitySet(CONTAINER, ES_PRODUCTS_NAME));
	      entitySets.add(getEntitySet(CONTAINER, ES_RESTAURANT_NAME));
	      // create EntityContainer
	      CsdlEntityContainer entityContainer = new CsdlEntityContainer();
	      entityContainer.setName(CONTAINER_NAME);
	      entityContainer.setEntitySets(entitySets);

	      return entityContainer;
	    }
}
