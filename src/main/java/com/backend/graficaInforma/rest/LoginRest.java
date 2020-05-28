package com.backend.graficaInforma.rest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.graficaInforma.dto.UserModel;
import com.backend.graficaInforma.dto.Users;
import com.backend.graficaInforma.repository.UsersRepository;
import com.backend.graficaInforma.security.User;
import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://10.191.190.15:7777", "http://10.191.190.9:7777",
		"http://intranet.telcel.com:9045" })
public class LoginRest {

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/backEGInforma/login")
	public ResponseEntity<UserModel> login(@RequestBody Users usuario) throws Exception {
		try {
			System.out.println("usuario: "+ usuario.getUsername() +" password: "+usuario.getPassword());
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));
			UserModel user = new UserModel();
			user.setUsername(auth.getName());
			user.setToken(getJWTToken(auth.getName()));
			List<String> autorities= new ArrayList<String>();
			for (GrantedAuthority m : auth.getAuthorities()) {
				autorities.add(m.toString());
			}
			user.setAutorities(autorities);
			if(!validaClave(usuario.getUsername(), usuario.getClave()) ) {
				throw new Exception("clave incorrecta");
			}
			return new ResponseEntity<UserModel>(user, HttpStatus.OK);
		} catch (BadCredentialsException e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}

	private String getJWTToken(String username) {

		String secretKey = "tokenTelcel";

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils

				.commaSeparatedStringToAuthorityList("ADMIN");

		System.out.println("TOKEN PREPARADO");

		String token = Jwts

				.builder()

				.setId("telcelInforma")

				.setSubject(username)

				.claim("authorities",

						grantedAuthorities.stream()

								.map(GrantedAuthority::getAuthority)

								.collect(Collectors.toList()))

				.setIssuedAt(new Date(System.currentTimeMillis()))

				.setExpiration(new Date(System.currentTimeMillis() + 600000 ))

				.signWith(SignatureAlgorithm.HS512,

//				.signWith(SignatureAlgorithm.ES256,

						secretKey.getBytes())
				.compact();

		System.out.println("REGRESANDO TOKEN");

		return "TELCELINFORMA " + token;

	}
	@Autowired
	private UsersRepository repository;
	
	public boolean validaClave(String usuario,  String clave) {
		List<Users> l = new ArrayList<Users>();
		l = repository.findByUsernameAndClave(usuario, clave);
		if (!l.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
