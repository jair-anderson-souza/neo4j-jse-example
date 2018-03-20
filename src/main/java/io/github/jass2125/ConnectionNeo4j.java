/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125;

import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

/**
 * @author Anderson Souza <jair_anderson_bs@hotmail.com>
 * @since Mar 20, 2018 6:16:26 AM
 */
public class ConnectionNeo4j {

    private final String uri = "bolt://localhost:7687";
    private final AuthToken authTokens = AuthTokens.basic("neo4j", "123");

    public Driver openConnection() {
        try {
            return GraphDatabase.driver(uri, authTokens);
        } catch (Exception e) {
            throw new ConnectionException("Invalid credentials", e);
        }
    }
}
