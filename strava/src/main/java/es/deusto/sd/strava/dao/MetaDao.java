package es.deusto.sd.strava.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;

@Repository
public class MetaDao {

    @Value("${external.meta.host}")
    private String metaHost;

    @Value("${external.meta.port}")
    private int metaPort;

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Valida si un email existe en meta-service vía socket.
     */
    public boolean validateEmail(String email) {
        try (Socket socket = new Socket(metaHost, metaPort);
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            // Construir request JSON
            ObjectNode req = mapper.createObjectNode();
            req.put("action", "validate");
            req.put("email", email);
            out.println(mapper.writeValueAsString(req));

            // Leer respuesta
            String line = in.readLine();
            JsonNode resp = mapper.readTree(line);
            return resp.path("result").asBoolean(false);
        } catch (Exception e) {
            throw new RuntimeException("Meta socket service unavailable", e);
        }
    }

    /**
     * Registra un usuario en meta-service vía socket.
     * Devuelve true si se creó, false si ya existía.
     */
    public boolean registerUser(String email, String name, LocalDate birthDate) {
        try (Socket socket = new Socket(metaHost, metaPort);
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            ObjectNode req = mapper.createObjectNode();
            req.put("action", "register");
            req.put("email", email);
            req.put("name", name);
            req.put("birthDate", birthDate.toString());
            out.println(mapper.writeValueAsString(req));

            String line = in.readLine();
            JsonNode resp = mapper.readTree(line);
            return resp.path("success").asBoolean(false);
        } catch (Exception e) {
            throw new RuntimeException("Meta socket service unavailable", e);
        }
    }
}
