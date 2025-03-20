package com.example.services;

import com.example.entities.Product;
import com.example.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
    public Product save(Product product) {
        Date date = new Date();//Şuanki Zamanı Basar veri tabanına banka uygulamalarında kullanılır.
        product.setDate(date);
         productRepository.save(product); //Set Id kullanımı için bu şekilde yazarız var olanı kullanır yeni oluşturmaz.
         return product;
    }

    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    //Sayfalam
    //Pagination
    //Bölümlenmiş safya
    //sayfa başına düşen item ürün sayısı


    public List<Product>all(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);// şu sayfadan başla şu kadar değer getir.
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.getContent();
        //Select * from product! New anahtar kelimesi yoksa bir maliyetten bahsetmeyiz.

    }
    //Value ile çağırılma komutları.
    public Product singleProduct(long pid) {
        Optional<Product>product = productRepository.findById(pid);
        return product.orElse(null);

    }

    //Product search
    public List<Product> searchProduct(String question )
    {
       return null;

    }

    public List<Product>notLike(String title){
        return productRepository.findByTitleNotLikeIgnoreCase("%"+title+"%");
    }
    public List<Product>like(String title){
        return productRepository.findByTitleLikeIgnoreCase("%"+title+"%");
    }
    public List<Product>greaterThan(BigDecimal price){
        return productRepository.findByPriceGreaterThan(price);
    }

    public List<Product>betweenTitlePrice(String title,BigDecimal priceStart, BigDecimal priceEnd){
        return productRepository.findByTitleIgnoreCaseAndPriceBetween(title,priceStart, priceEnd);
    }
    public List<Product>startsWith(String descriptionStart){
        return productRepository.findByDescriptionStartsWithIgnoreCase(descriptionStart);
    }
    public List<Product>endsWith(String descriptionEnd){
        return productRepository.findByDescriptionEndsWithIgnoreCase(descriptionEnd);
    }

    public List<Product>between(BigDecimal priceStart, BigDecimal priceEnd){
        return productRepository.findByPriceBetween(priceStart, priceEnd);
    }

    public List<Product>pidIn(Collection<Long> pids){
        return productRepository.findByPidNotIn(pids);
    }

    public List<Product>lessThan(BigDecimal price){
        return productRepository.findByPriceLessThan(price);
    }


    }






    // Türkçe Tarih kullanımı
  /*  public Product singleProduct(long pid) {
        Optional<Product> optionalProduct = productRepository.findById(pid);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            Date date = product.getDate();
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat ("dd MMMM yyyy HH:mm:ss", new Locale("tr","TR"));
            String dateString = simpleDateFormat.format(date);
            System.out.println(dateString);


        }
        return optionalProduct.orElse(null);
    }*/

