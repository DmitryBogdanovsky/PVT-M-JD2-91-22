package it.academy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductRestController {


    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDto>> getProducts(
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "5") int pageSize
    ) {
        return new ResponseEntity<>(
                List.of(
                        ProductDto.builder().id("1").name("iPhone 13").price(2499.49).build(),
                        ProductDto.builder().id("2").name("iPhone 14").price(3499.49).build()
                ),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductDto> getProduct(
            @PathVariable(name = "id") String id
    ) {
        return new ResponseEntity<>(
                ProductDto.builder().id(id).name("iPhone 13").price(2499.49).build(),
                HttpStatus.OK
        );
    }

    @PostMapping(
            value = "/products/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<ProductDto> postProduct(
            @PathVariable(name = "id") String id,
            @RequestBody ProductDto productDto
    ) {
        //TODO: validation logic and call DAO repository
        productDto.setId(id);
        return new ResponseEntity<>(
                productDto,
                new MultiValueMapAdapter<>(
                        Map.of("my.header", List.of("my.value"))
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping(
            value = "/products/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<ProductDto> putProduct(
            @PathVariable(name = "id") String id,
            @RequestBody ProductDto productDto
    ) {
        //TODO: 1. read entity from database by "id"
        //TODO: 2. validate current field value vs new field value
        //TODO: 3. update entity with new values
        //TODO: 4. save entity to database if at least one field has been changed

        productDto.setId(id);
        return new ResponseEntity<>(
                productDto,
                new MultiValueMapAdapter<>(
                        Map.of("my.header", List.of("my.value"))
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping(
            value = "/products/{id}"
    )
    public ResponseEntity<ProductDto> deleteProduct(
            @PathVariable(name = "id") String id
    ) {
        //TODO: delete entity from database
        return new ResponseEntity<>(
                new MultiValueMapAdapter<>(
                        Map.of("my.header", List.of("my.value"))
                ),
                HttpStatus.OK
        );
    }

}
