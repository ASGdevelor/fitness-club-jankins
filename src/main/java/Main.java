import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    private static final Logger logger =
            LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Properties properties = new Properties();

        try (InputStream input = Main.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (input == null) {
                logger.error("Файл application.properties не найден!");
                return;
            }

            properties.load(input);

            logger.info("Фитнес-клуб запущен!");
            logger.info("Название: {}", properties.getProperty("app.name"));
            logger.info("Среда: {}", properties.getProperty("app.environment"));

        } catch (IOException e) {
            logger.error("Ошибка загрузки конфигурации", e);
        }
    }
}
