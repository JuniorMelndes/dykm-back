package com.dykm.feature.service;

import java.util.stream.Collectors;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.dykm.utils.Utils;
import com.dykm.entity.Artist;
import com.dykm.entity.ReturnApiArtist;
import com.dykm.entity.RetornoApiUser;
import com.dykm.feature.entity.ResponseArtista;
import com.dykm.feature.entity.ResponseTop50;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiService {
	
	private final static WebClient client = WebClient.create();
	@Value("${app.uri}")
    private String uri;
	
	public ResponseArtista getArtista(String nameArtist, int position) {
		String newUri = uri;
		newUri = newUri.replace("{metodo}", "artist.getinfo");
		newUri = newUri.replace("{tipo}", "artist=" + nameArtist);
		newUri = newUri.replace("{key}", "6ab519e4b165dda0dbf5dac3c6e82977");
		try {
			ReturnApiArtist response =client.get()
			.uri(newUri)
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToMono(ReturnApiArtist.class)
            .block();
			
			String birthOrFoundation = Utils.getBirthOrFoundation(response.getArtist().getBio().getSummary());
			ResponseArtista responseArtista = new ResponseArtista(response.getArtist().getName(),
					  											  response.getArtist().getStatus().getListeners(),
					  											  response.getArtist().getStatus().getScrobbles(),
					  											  position,
					  											  birthOrFoundation,
																  response.getArtist().getSimilar().getSimilarArtists().stream().map(Artist::getName).collect(Collectors.toList()),
																  response.getArtist().getTag().getTags().stream().map(Artist::getName).collect(Collectors.toList())
																  );
			
			return responseArtista;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public NameAndPosition getRandomTop50(String user) {
		String newUri = uri;
		newUri = newUri.replace("{metodo}", "user.gettopartists");
		newUri = newUri.replace("{tipo}", "user=" + user);
		newUri = newUri.replace("{key}", "6ab519e4b165dda0dbf5dac3c6e82977");
		try {
			RetornoApiUser response =client.get()
			.uri(newUri)
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToMono(RetornoApiUser.class)
            .block();
			ResponseTop50 top50 = new ResponseTop50(response.getTopArtistas().getTopArtistas().stream().map(Artist::getName).collect(Collectors.toList()));
			Random random = new Random();
			int number = random.nextInt(50);
			String nameRandomArtist = top50.getTopBandas().get(number);
			return new NameAndPosition(nameRandomArtist , number);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public static record NameAndPosition(String name, int number) {}
	
}
