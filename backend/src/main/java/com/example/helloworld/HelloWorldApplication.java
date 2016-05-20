package com.example.helloworld;

import java.sql.SQLException;

import org.skife.jdbi.v2.DBI;

import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.HelloWorldResource;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import se.bth.softhouse.db.FilterDAO;
import se.bth.softhouse.process.FilterProcess;
import se.bth.softhouse.resources.FilterResource;

public class HelloWorldApplication extends Application<HelloWorldConfiguration>
{
	public static void main(String[] args) throws Exception
	{
		new HelloWorldApplication().run(args);
	}

	@Override
	public String getName()
	{
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap)
	{
		// nothing to do yet
	}

	@Override
	public void run(HelloWorldConfiguration configuration, Environment environment) throws SQLException
	{
		
		//resources
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDatabaseConfiguration(), "h2");
		org.h2.tools.Server myH2adminGUI = org.h2.tools.Server.createWebServer("-webDaemon");
		myH2adminGUI.start();
		FilterDAO filterDAO = jdbi.onDemand(FilterDAO.class);
		
		filterDAO.createFilterTable();
		
		final FilterProcess filterProcess = new FilterProcess(filterDAO);
		
		final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());
		final FilterResource filterResource = new FilterResource(filterProcess);
		
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		
		environment.jersey().register(resource);
		environment.jersey().register(filterResource);
		//environment.jersey().register(audioFileResource);
	}

}