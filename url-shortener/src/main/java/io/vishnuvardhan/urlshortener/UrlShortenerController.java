package io.vishnuvardhan.urlshortener;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UrlShortenerController {

	private final JdbcClient jdbcClient;
	private final String baseUrl; // Replace with your actual base URL

	public UrlShortenerController(JdbcClient jdbcClient, @Value("${short-link-base-url}") String baseUrl) {
		this.jdbcClient = jdbcClient;
		this.baseUrl = baseUrl;
	}

	@PostMapping("/urlshortener")
	@ResponseStatus(HttpStatus.CREATED)
	public String shortenUrl(@RequestBody String longUrl) {

		if (!longUrl.startsWith("http://") && !longUrl.startsWith("https://")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		KeyHolder key = new GeneratedKeyHolder();
		System.out.println("longUrl: " + longUrl);

		System.out.println("longUrl: " + longUrl);
		jdbcClient.sql("INSERT INTO short_link  (long_url) VALUES (?)").param(longUrl).update(key,"ID");

		String shortCode = Base62.encode(key.getKey().longValue());
		return baseUrl + shortCode;

	}

	@GetMapping("/*")
	ResponseEntity<Void> redirect(HttpServletRequest request) {
		

		String code = request.getRequestURI().substring(1);

		List<String> longUrls = jdbcClient.sql("SELECT long_url FROM short_link WHERE id = ?")
				.params(Base62.decode(code)).query(String.class).list();
		if (longUrls.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		System.out.println(">>> redirect request reached server for code: " + code);
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(longUrls.get(0))).build();

	}

}
