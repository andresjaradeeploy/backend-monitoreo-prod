package com.monitor.bankendmonitoreoLinks.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.monitor.bankendmonitoreoLinks.dao.TokenDto;
import com.monitor.bankendmonitoreoLinks.entity.admin.Rol;
import com.monitor.bankendmonitoreoLinks.entity.admin.Usuario;
import com.monitor.bankendmonitoreoLinks.enums.RolNombre;
import com.monitor.bankendmonitoreoLinks.security.jwt.JwtProvider;
import com.monitor.bankendmonitoreoLinks.service.imp.RolServiceImp;
import com.monitor.bankendmonitoreoLinks.service.imp.UsuarioServiceImp;


@RestController
@RequestMapping("/oauth")
@CrossOrigin(origins = { "https://monitoreo-ads-fb.web.app","https://enki.com.co","http://localhost:4200" })
//@CrossOrigin(origins = { "http://localhost:4200", "https://monitoreo-ads-fb.web.app","*" })
public class OauthRestController {

	@Value("${google.clientId}")
	String googleClientId;

	@Value("${secretPsw}")
	String secretPsw;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	UsuarioServiceImp usuarioService;

	@Autowired
	RolServiceImp rolService;

	@PostMapping("/google")

	public ResponseEntity<TokenDto> google(@RequestBody TokenDto tokenDto) throws IOException {
		final NetHttpTransport transport = new NetHttpTransport();
		final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
		GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
				.setAudience(Collections.singletonList(googleClientId));
		final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getValue());
		final GoogleIdToken.Payload payload = googleIdToken.getPayload();
		Usuario usuario = new Usuario();
		if (usuarioService.existsEmail(payload.getEmail())) {

			usuario = usuarioService.getByEmail(payload.getEmail()).get();
			usuario.setEmail(payload.getEmail());
			usuario.setPassword(passwordEncoder.encode(secretPsw));
			usuario.setCargo(usuario.getCargo());
			usuario.setNombre(usuario.getNombre());

			Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
			Set<Rol> roles = new HashSet<>();
			roles.add(rolUser);
			usuario.setRoles(roles);
			
			usuarioService.save(usuario);

		} else {
			
				System.err.println("El usuario"+ payload.getEmail()+"no pertenece al aplicativo");
		}

		show(payload.getEmail());
		TokenDto tokenRes = login(usuario);

		return new ResponseEntity<TokenDto>(tokenRes, HttpStatus.OK);

	}

	private TokenDto login(Usuario usuario) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), secretPsw));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		TokenDto tokenDto = new TokenDto();
		tokenDto.setValue(jwt);
		return tokenDto;
	}

	
	@GetMapping("/cliente/{email}")
	public Optional<Usuario> show(@PathVariable String email) {
		return usuarioService.findByEmail(email);
	}


}
