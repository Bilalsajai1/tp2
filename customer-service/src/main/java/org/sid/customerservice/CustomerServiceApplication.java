package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.respostories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository , RepositoryRestConfiguration repoRestConfiguration) {
        repoRestConfiguration.exposeIdsFor(Customer.class);
        return args -> {
            customerRepository.save(new Customer(null,"Bilal", "Bilal@Gmail.com"));
            customerRepository.save(new Customer(null,"anass", "anass@Gmail.com"));
            customerRepository.save(new Customer(null,"khalid", "khalid@Gmail.com"));
            customerRepository.findAll().forEach(
                    customer -> System.out.println(customer.toString())
            );
        };
    }

}


