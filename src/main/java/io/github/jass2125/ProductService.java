/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125;

import java.io.Serializable;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.Values;

/**
 * @author Anderson Souza <jair_anderson_bs@hotmail.com>
 * @since Mar 20, 2018 6:56:22 AM
 */
public class ProductService implements Serializable {

    private ConnectionNeo4j connection;
    private Driver driver;

    public ProductService() {
        this.connection = new ConnectionNeo4j();
        this.driver = this.connection.openConnection();
    }

    public void createProduct(Product product) {
        try (Session session = driver.session()) {
            session.run("CREATE (p:Product{code : $code, description : $description, price : $price}) RETURN id(p) as id",
                    Values.parameters("code", product.getCode(), "description", product.getDescription(), "price", product.getPrice()));
        }
    }

    public Product getProductById(String code) {
        try (Session session = driver.session()) {
            StatementResult result = session.run("MATCH (p:Product) "
                    + "WHERE p.code = $code "
                    + "RETURN p.code as code, " + "p.description as description, " + "p.price as price", Values.parameters("code", code));
            while (result.hasNext()) {
                Record record = result.next();
                String codeTemp = record.get("code").asString();
                String description = record.get("description").asString();
                float price = record.get("price").asFloat();
                return new Product(codeTemp, description, price);
            }
            return null;
        }
    }

    public void createRelationShip(String code, String code2) {
        try (Session session = driver.session()) {
            session.run("MATCH (p:Product), (p2:Product) "
                    + "WHERE p.code = $code AND p2.code = $code2 "
                    + "CREATE (p)-[r:FRIEND]->(p2) "
                    + "RETURN p, p2, r",
                    Values.parameters("code", code, "code2", code2));
        }
    }
}
