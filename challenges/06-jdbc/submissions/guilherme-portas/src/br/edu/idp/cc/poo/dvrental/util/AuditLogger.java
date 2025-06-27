package br.edu.idp.cc.poo.dvrental.util;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditLogger {
    private static final Path LOG_PATH = Paths.get("logs/audit.log");

    public static void log(String action, String details) {
        String logEntry = String.format("[%s] %s - %s%n", LocalDateTime.now(), action, details);
        try {
            Files.createDirectories(Paths.get("logs"));
            Files.writeString(LOG_PATH, logEntry, StandardCharsets.UTF_8,
                              StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo de log: " + e.getMessage());
        }
    }
}

