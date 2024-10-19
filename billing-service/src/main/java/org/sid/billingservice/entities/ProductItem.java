package org.sid.billingservice.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.sid.billingservice.model.Product;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private long productId;
    private double price;
    private double quantity;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Bill bill;
    @Transient
    private Product product;
}
