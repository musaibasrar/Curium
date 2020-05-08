/**
 * 
 */
package org.ideoholic.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.ideoholic.controller.infrastructure.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author springboard
 *
 */
@SpringBootApplication
public class CuriumSpringApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CuriumSpringApplication.class);
    }
    
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(ApplicationConfiguration.class, args);
		waitForKeyPressToCleanlyExit(ctx);
	}
	
    public static void waitForKeyPressToCleanlyExit(ConfigurableApplicationContext ctx) throws IOException {

        // NOTE: In Eclipse, the Shutdown Hooks are not invoked on exit (red
        // button).. In the case of MariaDB4j that's a problem because then the
        // mysqld won't be stopped, so:
        // (@see https://bugs.eclipse.org/bugs/show_bug.cgi?id=38016)
        System.out.println("\nHit Enter to quit...");
        // NOTE: In Eclipse, System.console() is not available.. so:
        // (@see https://bugs.eclipse.org/bugs/show_bug.cgi?id=122429)
        BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
        d.readLine();

        ctx.stop();
        ctx.close();
    }


    /*public static void main(String[] args) {
        SpringApplication.run(CuriumSpringApplication.class, args);
    }*/

}
