/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125;

/**
 * @author Anderson Souza <jair_anderson_bs@hotmail.com>
 * @since Mar 20, 2018 6:28:29 AM 
 */
public class ConnectionException extends RuntimeException {

    public ConnectionException(String sdf, Exception e) {
        super(sdf, e);
    }

}
