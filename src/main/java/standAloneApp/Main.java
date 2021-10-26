package standAloneApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import standAloneApp.frontend.GUI;

import java.awt.*;


@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        //SpringApplication app = new SpringApplication(Main.class);
        //app.setBannerMode(Banner.Mode.OFF);
        //app.setHeadless(false);
        //app.run(args);
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Main.class).headless(false).run();
        EventQueue.invokeLater(() ->{
            GUI ex = ctx.getBean(GUI.class);
            ex.setVisible(true);

        });
    }

    @Override
    public void run(String... args) throws Exception{

    }
}
