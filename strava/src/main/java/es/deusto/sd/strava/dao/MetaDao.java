package es.deusto.sd.strava.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.Socket;

@Repository
public class MetaDao {
    private final String host;
    private final int port;
    private final ObjectMapper mapper = new ObjectMapper();

    public MetaDao(@Value("${external.meta.host:localhost}") String host,
                   @Value("${external.meta.port:9090}") int port) {
        this.host = host;
        this.port = port;
    }

    public boolean validateEmail(String email) {
        try (Socket socket = new Socket(host, port);
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            ObjectNode req = mapper.createObjectNode();
            req.put("action", "validate");
            req.put("email", email);
            String jsonReq = mapper.writeValueAsString(req);
            out.write(jsonReq);
            out.newLine();
            out.flush();

            String jsonResp = in.readLine();
            ObjectNode resp = (ObjectNode) mapper.readTree(jsonResp);
            return resp.get("result").asBoolean(false);
        } catch (IOException e) {
            throw new RuntimeException("Meta service unavailable", e);
        }
    }

    public boolean registerUser(String email, String name, String birthDate) {
        try (Socket socket = new Socket(host, port);
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            ObjectNode req = mapper.createObjectNode();
            req.put("action", "register");
            req.put("email", email);
            req.put("name", name);
            req.put("birthDate", birthDate);
            String jsonReq = mapper.writeValueAsString(req);
            out.write(jsonReq);
            out.newLine();
            out.flush();

            String jsonResp = in.readLine();
            ObjectNode resp = (ObjectNode) mapper.readTree(jsonResp);
            return resp.get("success").asBoolean(false);
        } catch (IOException e) {
            throw new RuntimeException("Meta service unavailable", e);
        }
    }
}

