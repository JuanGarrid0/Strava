package es.deusto.sd.meta;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MetaServer {
    private final int port;
    private final Map<String, UserMeta> users = new ConcurrentHashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public MetaServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("MetaServer listening on port " + port);
            while (true) {
                Socket client = serverSocket.accept();
                new Thread(() -> handle(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle(Socket client) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                sb.append(line);
            }
            ObjectNode request = (ObjectNode) mapper.readTree(sb.toString());
            String action = request.get("action").asText();
            ObjectNode response = mapper.createObjectNode();
            switch (action) {
                case "validate": {
                    String email = request.get("email").asText();
                    response.put("result", users.containsKey(email));
                    break;
                }
                case "register": {
                    String email = request.get("email").asText();
                    if (users.containsKey(email)) {
                        response.put("success", false);
                    } else {
                        String name = request.get("name").asText();
                        LocalDate bd = LocalDate.parse(request.get("birthDate").asText());
                        UserMeta user = new UserMeta(email, name, bd);
                        users.put(email, user);
                        response.put("success", true);
                    }
                    break;
                }
                default:
                    response.put("error", "Unknown action");
            }
            String jsonResp = mapper.writeValueAsString(response);
            out.write(jsonResp);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

