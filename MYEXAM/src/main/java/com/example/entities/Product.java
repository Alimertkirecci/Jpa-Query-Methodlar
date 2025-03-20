package com.example.entities;

import com.example.utils.EProduct;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//ID Verip Veri tabanını şekillendirir bir bir artır
    private Long pid; //Null denetim yapabilmek için Long kullanırız.
    @Size(min = 2, max = 200)
    @NotEmpty
    @NotNull
    private String title;
    @Size(min = 3, max = 500)
    @NotEmpty
    @NotNull
    private String description;
    @NotNull
    @Enumerated
    private EProduct status;// Gelen değerin kalıbı belli o yüzden safece NotNull denetimi yapar. Eproductan geliyor verileri.
    //içinde gelecek değerler belli


    @Max(50000000)
    @Min(150)
    @NotNull
    private BigDecimal price;// ondalık değerin yerine kullancağımız matematiksel işlemler burada geçer.size gerekli renk kodu için min max 6 olmalı
    @Size(min = 7, max = 7)
    private String color;// notnull olabilir boş bırakılabilir çok fazla renk var önü açık olmalı.
 //  @DateTimeFormat(pattern = "yyyy", iso = DateTimeFormat.ISO.DATE) //tr ye uyarlanmış hali.
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")

    private Date date; //Formatı belli

    //Status
    //forSale , forRent,leasing

}
