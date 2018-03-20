package io.github.jass2125;

public class Loader {

    public static void main(String[] args) {
    
        Product product = new Product("1111", "product", 10);
        Product product2 = new Product("2222", "product2", 20);
        ProductService service = new ProductService();
        service.createProduct(product);
        service.createProduct(product2);
        
        Product prod = service.getProductById("1111");
        System.out.println("Product: " + prod);
        
        Product prod2 = service.getProductById("2222");
        System.out.println("Product: " + prod2);
        
        service.createRelationShip("1111", "2222");
        
        
    }
}
