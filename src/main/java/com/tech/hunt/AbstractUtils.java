package com.tech.hunt;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public abstract class AbstractUtils {
	protected static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
}
