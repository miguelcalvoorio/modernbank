package org.modernbank.frontend.teller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Optional;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ConfigUtils;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class TellerApplicationLifeCycle {
    @Inject Logger logger;

    @ConfigProperty(name = "org.modernbank.banner.path")
    Optional<String> bannerFilePath;

    void onStart(@Observes StartupEvent ev) {
        showBanner();

        logger.info("TELLER UI microservice is starting with profile " + ConfigUtils.getProfiles());
    }

    void onStop(@Observes ShutdownEvent ev) {
        logger.info("The microservice TELLER UI is stopping...");
    }

    private void showBanner() {
        if (bannerFilePath.isPresent()) {
            try {
                // Get banner file URL
                InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(bannerFilePath.get());
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    //logger.info(line);
                    System.out.println(line);
                }
                inputStream.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
