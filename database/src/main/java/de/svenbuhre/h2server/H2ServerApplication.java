package de.svenbuhre.h2server;

import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class H2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(H2ServerApplication.class, args);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }
}
