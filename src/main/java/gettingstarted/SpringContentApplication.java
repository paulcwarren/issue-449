package gettingstarted;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringContentApplication {

    private static final Log logger = LogFactory.getLog(SpringContentApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringContentApplication.class, args);
    }

    @Configuration
    @EnableJpaRepositories(basePackages = {"gettingstarted"})
    public static class StoreConfig {
    }

    @Component
    public class MyRunner implements CommandLineRunner {

        @Autowired
        private FileRepository repository;

        @Autowired
        private FileContentStore store;

        @Override
        public void run(String... args) throws Exception {
            repository.deleteAll();

            File f = new File();
            f = store.setContent(f, this.getClass().getResourceAsStream("/sample.pdf"));
            f = repository.save(f);

            logger.info("File uploaded");
        }
    }
}

