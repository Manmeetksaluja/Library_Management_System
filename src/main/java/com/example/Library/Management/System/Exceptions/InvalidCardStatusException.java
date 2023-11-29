package com.example.Library.Management.System.Exceptions;
//to show ki maybe card is blocked , or card abhi issuehone gya hua hai bt abhi hua ni hai
public class InvalidCardStatusException extends Exception{
    public InvalidCardStatusException(String message) {
        super(message);
    }
}
