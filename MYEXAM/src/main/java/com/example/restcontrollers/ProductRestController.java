package com.example.restcontrollers;

import com.example.entities.Product;
import com.example.repositories.ProductRepository;
import com.example.services.ProductService;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.hibernate.query.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
@Validated//Kullanıcım tarafından hatayı yakalamak istiyorusam
@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductRestController {
     final ProductService productService;
     @PostMapping("save")
    public Product save(@Valid @RequestBody Product product) {
        return productService.save(product);
     }
    @PostMapping("saveAll") //Servis kısmında List kullandığımız için burada da list bekler.
    public List<Product> saveAll(@Valid @RequestBody List<Product> products) {//request bir HTTP isteğinin gövdesini bir denetleyici işleyici yöntemindeki bir yöntem parametresine bağlamak için
         return productService.saveAll(products);
    }

     @GetMapping("list")// Burada Hata Olma olasılığı çok az hiç veri yoksa boş dizi döner.
    public List<Product> list (

             //@RequestParam:Teknik adı = QueryString tekil olarak gelen datalarını yakalamak için kullanılır.defaultValue değer gelmezse varsayılan.
              @Min(0)@Max(20)@RequestParam(defaultValue = "0") int page,//bu sayfadan başla
             @Min(0)@Max(20)@RequestParam(defaultValue = "5") int size// bu kadar değer getir.
             //Değer aralığını yakalamak için
     ){
         return productService.all(page,size);

     }

     @GetMapping("single")
    public Product single(

          @RequestParam(defaultValue = "1") long pid,
          @RequestParam(defaultValue = "0") int cid // kategory ID
       )
          // @RequestParam:Teknik adı = QueryString tekil olarak gelen datalarını yakalamak için kullanılır.
     {
         System.out.println(cid);
         return productService.singleProduct(pid);//veritabanında döndercek halde olur.

     }
     @GetMapping("search")
    public List<Product>findByTitleLikeIgnoreCase(@RequestParam String title) {
         return productService.like(title);
     }
    @GetMapping("separate")//Ayrıtutma
    public List<Product>findByTitleNotLikeIgnoreCase(@RequestParam String title) {
        return productService.notLike(title);
    }
    @GetMapping("start")
    public List<Product>findByDescriptionStartsWithIgnoreCase(@RequestParam String descriptionStart) {
        return productService.startsWith(descriptionStart);
    }
    @GetMapping("end")
    public List<Product>findByDescriptionEndsWithIgnoreCase(@RequestParam String descriptionEnd) {
        return productService.endsWith(descriptionEnd);
    }
    @GetMapping("minmax")
    public List<Product>findByPriceBetween(@RequestParam BigDecimal priceStart, @RequestParam BigDecimal priceEnd) {
        return productService.between(priceStart,priceEnd);
    }
    @GetMapping("filt")
    public List<Product>betweenTitlePrice(@RequestParam String title, @RequestParam BigDecimal priceStart, @RequestParam BigDecimal priceEnd) {
        return productService.betweenTitlePrice(title,priceStart,priceEnd);
    }

    @GetMapping("InExpensive")
    public List<Product>lessThan( @RequestParam BigDecimal price) {
        return productService.lessThan(price);
    }
    @GetMapping("Expensive")
    public List<Product>greaterThan( @RequestParam BigDecimal price) {
        return productService.greaterThan(price);
    }
    @GetMapping("Pid")
    public List<Product>findByPidNotIn( @RequestParam Collection<Long> pids ) {
        return productService.pidIn(pids);
    }










}
