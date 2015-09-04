package rrs.rest.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/api")
public class RestApp extends ResourceConfig {

	public RestApp() {
		packages("rrs.controllers");

		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0");
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setBasePath("/RRS-Rest/api");
		beanConfig.setResourcePackage("rrs.controllers");
		beanConfig.setTitle("Restaurant Reservation System API Documentation");
		beanConfig.setDescription("REST API for Restaurant Reservation System");
		beanConfig.setScan(true);
	}
}
