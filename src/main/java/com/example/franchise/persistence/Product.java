package com.example.franchise.persistence;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "producto")
public class Product {

    @Id
    private String id;
    private String name;
    private String stock;
}
