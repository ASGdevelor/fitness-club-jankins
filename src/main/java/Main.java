import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        Properties properties = new Properties();

        try (InputStream input = Main.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (input == null) {
                System.out.println("Файл application.properties не найден!");
                return;
            }

            properties.load(input);

            System.out.println("Фитнес-клуб запущен!");
            System.out.println("Название: " +
                    properties.getProperty("app.name"));
            System.out.println("Среда: " +
                    properties.getProperty("app.environment"));

        } catch (IOException e) {
            System.out.println("Ошибка загрузки конфигурации:");
            e.printStackTrace();
        }
    }
}