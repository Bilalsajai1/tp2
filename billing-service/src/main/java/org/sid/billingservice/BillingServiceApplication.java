package org.sid.billingservice;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductRestClient;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repositories.BillRepository;
import org.sid.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productItemRestClient){
        return args -> {
            Customer customer = customerRestClient.getCustomerById(1L);
            PagedModel<Product> productPagedModel = productItemRestClient.pageProducts(0,0);
            Bill bill = billRepository.save(new Bill(null, new Date(), customer.getId(), null, null));

            productPagedModel.forEach(p->{

                ProductItem productItem = new ProductItem();
                productItem.setProductId(p.getId());
                productItem.setPrice(p.getPrice());
                productItem.setQuantity((int)(1+Math.random()*100));
                productItem.setBill(bill);
                productItemRepository.save(productItem);
            });
        };
    }

}
