package com.taslim.springSecurity.utils;

import com.taslim.springSecurity.entity.UserEntity;
import com.taslim.springSecurity.exception.UserNotFoundException;
import com.taslim.springSecurity.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

/**
 * Utility class for handling JSON Web Tokens (JWT) operations, including token generation, validation, and extraction.
 */
@Component
@RequiredArgsConstructor
public class JwtService {

    private final UserRepository userRepository;

    // Secret key for JWT signing. In a real-world scenario, store this securely and do not expose it in code.
    private static final String SECRET_KEY = "26452948404D635166546A576E5A7234753778214125442A462D4A614E645267";

    /**
     * Generates a JWT token for the provided UserDetails.
     *
     * @param userDetails The UserDetails for which the token is generated.
     * @return The generated JWT token.
     */
    public String generateToken(UserDetails userDetails){
        Map<String, Object> authorities = new HashMap<>();
        authorities.put("authorities", userDetails.getAuthorities());
        return generateToken(authorities, userDetails);
    }

    /**
     * Generates a JWT token with additional claims for the provided UserDetails.
     *
     * @param extraClaims Additional claims to be included in the token.
     * @param userDetails The UserDetails for which the token is generated.
     * @return The generated JWT token.
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24*10))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Checks if a JWT token is valid for the provided UserDetails.
     *
     * @param token The JWT token to be validated.
     * @param userDetails The UserDetails for which the token is validated.
     * @return True if the token is valid, false otherwise.
     */
    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username);
        if(userEntity == null){
            throw new UserNotFoundException("Invalid token.");
        }
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Checks if a JWT token has expired.
     *
     * @param token The JWT token to be checked.
     * @return True if the token has expired, false otherwise.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from a JWT token.
     *
     * @param token The JWT token from which to extract the expiration date.
     * @return The expiration date.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts the username from a JWT token.
     *
     * @param jwt The JWT token from which to extract the username.
     * @return The username.
     */
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    /**
     * Extracts a specific claim from a JWT token using a claims resolver function.
     *
     * @param token The JWT token from which to extract the claim.
     * @param claimsResolver The function used to resolve the claim.
     * @param <T> The type of the claim.
     * @return The extracted claim.
     */
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from a JWT token.
     *
     * @param token The JWT token from which to extract all claims.
     * @return The claims.
     */
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Gets the signing key used for JWT token generation.
     *
     * @return The signing key.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
