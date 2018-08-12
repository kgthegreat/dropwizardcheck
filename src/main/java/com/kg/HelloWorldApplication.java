package com.kg;

import com.kg.health.TemplateHealthCheck;
import com.kg.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
            new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        //super.initialize(bootstrap);
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {

        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck templateHC = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", templateHC);
        environment.jersey().register(resource);
    }
}
