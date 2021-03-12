/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.customexception;

/**
 *
 * @author Leyteris
 */
public class IncorrectWorkingHours extends Exception {
    public IncorrectWorkingHours(String errorMessage) {
        super(errorMessage);
    }
}
