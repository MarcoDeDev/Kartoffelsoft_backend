package com.farmit.kartoffelsoft_backend.runner;

import com.farmit.kartoffelsoft_backend.model.Abteilung;
import com.farmit.kartoffelsoft_backend.model.Mitarbeiter;
import com.farmit.kartoffelsoft_backend.model.Role;
import com.farmit.kartoffelsoft_backend.repository.AbteilungRepository;
import com.farmit.kartoffelsoft_backend.repository.MitarbeiterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner initDatabase(MitarbeiterRepository mitarbeiterRepository,
                                          AbteilungRepository abteilungRepository,
                                          BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            // Überprüfe, ob bereits eine Admin-Abteilung existiert
            Abteilung adminAbteilung = abteilungRepository.findByAbteilungName("Admin").orElseGet(() -> {
                Abteilung abteilung = new Abteilung();
                abteilung.setAbteilungName("Admin");
                System.out.println("Admin-Abteilung erstellt.");
                return abteilungRepository.save(abteilung);
            });

            // Überprüfe, ob bereits ein Admin-Benutzer existiert
            if (mitarbeiterRepository.findByUsername("admin").isEmpty()) {
                Mitarbeiter admin = new Mitarbeiter();
                admin.setVorname("Admin");
                admin.setNachname("User");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123")); // Password gehasht
                admin.setRole(Role.ADMIN);
                admin.setAbteilung(adminAbteilung); // Setze die Admin-Abteilung hier
                mitarbeiterRepository.save(admin);
                System.out.println("Standard-Admin-Benutzer erstellt.");
            }
        };
    }
}