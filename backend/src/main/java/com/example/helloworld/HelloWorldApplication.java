package com.example.helloworld;

import java.sql.SQLException;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.skife.jdbi.v2.DBI;

import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import com.google.common.base.Joiner;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import se.bth.softhouse.db.FilterDAO;
import se.bth.softhouse.db.UserDAO;
import se.bth.softhouse.process.FilterProcess;
import se.bth.softhouse.resources.AudioResource;
import se.bth.softhouse.resources.FilterResource;
import se.bth.softhouse.resources.UserResource;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
	public static void main(String[] args) throws Exception {
		new HelloWorldApplication().run(args);
	}

	private static final String API_URL_PATTERN = "/api/*";

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(HelloWorldConfiguration configuration, Environment environment) throws SQLException {
		configureCrossOriginFilter(configuration, environment);
		environment.jersey().register(MultiPartFeature.class);
		// resources
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDatabaseConfiguration(), "h2");
		org.h2.tools.Server myH2adminGUI = org.h2.tools.Server.createWebServer("-webDaemon");
		myH2adminGUI.start();
		FilterDAO filterDAO = jdbi.onDemand(FilterDAO.class);

		filterDAO.createFilterTable();

		final FilterProcess filterProcess = new FilterProcess(filterDAO);

		final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(),
				configuration.getDefaultName());
		final FilterResource filterResource = new FilterResource(filterProcess);
		final AudioResource audioResource = new AudioResource();
		final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
		;
		final UserResource userResource = new UserResource(userDAO);

		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);

		environment.jersey().register(resource);
		environment.jersey().register(filterResource);
		environment.jersey().register(audioResource);
		environment.jersey().register(userResource);
	}

	private void configureCrossOriginFilter(HelloWorldConfiguration configuration, Environment environment) {
		String[] allowedOrigins = configuration.getAllowedOrigins();
		if (allowedOrigins == null || allowedOrigins.length == 0) {
			return;
		}

		Dynamic filter = environment.servlets().addFilter("CrossOriginFilter", CrossOriginFilter.class);
		filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, API_URL_PATTERN);
		filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, Joiner.on(',').join(allowedOrigins));
		filter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,
				"X-Requested-With,Content-Type,Accept,Accept-Language,Origin,Authorization");
		filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,PUT,DELETE");
		filter.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
	}

}