package org.sid.billingservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.billingservice.model.Customer;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Date billDate;
    @OneToMany(mappedBy="bill")
    private Collection<ProductItem> productItems;
    private Long customerId;
    //ceci n'est pas une entite JPA
    @Transient
    //l'attribut est dans la classe ms n'est pas represente dans la base de donnees,cad ignore par jpa
    private Customer customer;
}
