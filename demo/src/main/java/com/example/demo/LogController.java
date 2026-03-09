package com.example.demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);
    private Random random = new Random();
    @GetMapping("/error/null")
    public String createNullError() {
        String text = null;
        text.length(); // ❌ This line throws NullPointerException
        return "This will never execute";
    }
    @GetMapping("/generate-log")
    public String generateLog() {

        int type = random.nextInt(5);

        switch (type) {
            case 0:
                logger.info("User login successful for userId=1024");
                return "INFO log generated";

            case 1:
                logger.warn("API response delayed more than 3 seconds");
                return "WARN log generated";

            case 2:
                try {
                    String str = null;
                    str.length();
                } catch (Exception e) {
                    logger.error("NullPointerException occurred in UserService", e);
                }
                return "ERROR log generated";

            case 3:
                logger.error("Database connection failed: Timeout after 30s");
                return "DB ERROR log generated";

            case 4:
                logger.error("Payment gateway returned 502 Bad Gateway");
                return "External API ERROR log generated";

            default:
                return "No log generated";
        }
    }
}
