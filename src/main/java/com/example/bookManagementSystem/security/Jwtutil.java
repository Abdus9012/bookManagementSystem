//serves as a utility class for handling operations related to JSON Web Tokens (JWT).
// It encapsulates methods for generating, validating, and extracting information from JWTs.

package com.example.bookManagementSystem.security;

// import java.nio.charset.MalformedInputException;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class Jwtutil {
    private final String SECRET_KEY = "mysecretkey";
    private final long EXPIRATION_DATE = 86400000;

    //generate token

    public String generateToken(String username){
        return Jwts.builder() //callin builder to build
                   .setSubject(username) //setting the username
                   .setIssuedAt(new Date()) //setting the issued date
                   .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE)) //setting the expiration date
                   .compact(); //returning all the built token as a "string"
    }

    //extract username from the given token
    public String extractUsername(String token){
        return Jwts.parser() //calling parser to extract
           .setSigningKey(SECRET_KEY) //verifies signature of the token using secret key
           .parseClaimsJws(token) //deepdives to check the integrity of the token
           .getBody() .getSubject(); //exyracts the username for the token taken
    }

    //validate that token for security purposes
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }
        catch(ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e){
            return false;
        }
    }
}
