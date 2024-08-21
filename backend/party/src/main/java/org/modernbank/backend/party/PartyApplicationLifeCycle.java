package org.modernbank.backend.party;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ConfigUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.modernbank.backend.party.mapper.PartyMapper;
import org.modernbank.backend.party.model.Party;
import org.modernbank.backend.party.model.PartyRepository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ApplicationScoped
public class PartyApplicationLifeCycle {
    @Inject Logger logger;
    @Inject PartyRepository repository;
    @Inject PartyMapper partyMapper;

    @ConfigProperty(name = "org.modernbank.services.party.mongodb.initialloadfile.path")
    Optional<String> initialLoadFilePath;

    @ConfigProperty(name = "org.modernbank.services.party.mongodb.max-data-to-load")
    Optional<Integer> maxDataToLoad;

    @ConfigProperty(name = "org.modernbank.banner.path")
    Optional<String> bannerFilePath;

    @ConfigProperty(name = "quarkus.oidc.auth-server-url")
    Optional<String> authServer;

    void onStart(@Observes StartupEvent ev) {
        showBanner();

        logger.info("PARTY microservice is starting with profile " + ConfigUtils.getProfiles());

        loadInitialData();
    }

    void onStop(@Observes ShutdownEvent ev) {
        logger.info("The microservice PARTY is stopping...");
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

    private void loadInitialData() {      
        if (initialLoadFilePath.isPresent()) {
            repository.count().subscribe().with(i -> {
                logger.info("Database DEV/TEST 'parties' collection items: " + i);
                if (i==0) {
                    // Database is empty: load synthetic data
                    try {
                        // Create ObjectMapper instance
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.registerModule(new JavaTimeModule());
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
                        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(initialLoadFilePath.get());

                        List<Party> parties = Arrays.asList(mapper.readValue(inputStream, Party[].class));

                        if (maxDataToLoad.isPresent()) {
                            // Limit the number of parties to load
                            parties = parties.subList(0, Integer.min(parties.size(), maxDataToLoad.get().intValue()));
                        }

                        parties.forEach(p -> {
                            repository.persist(Stream.of(p)).await().indefinitely();
                            logger.info("New Party added -> " + p.getUuid() + ": " + p.getLastName() + ", " + p.getName());
                        });

                        logger.info("Initial load COMPLETED (" + parties.size() + " new parties)");
        
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }, RuntimeException::new);
        }
    }
}
