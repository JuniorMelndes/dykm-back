package com.dykm.feature;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.dykm.feature.entity.ResponseArtista;
import com.dykm.feature.service.ApiService;
import com.dykm.feature.service.ApiService.NameAndPosition;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DykmService {
	private final ApiService api;
	
	public ResponseArtista choseArtist(String nomeUsuario) {
		NameAndPosition retorno = api.getRandomTop50(nomeUsuario);
		int position = retorno.number() + 1;
		String nameArtist = retorno.name();
		return api.getArtista(nameArtist, position);
	}
	
	public UserAndArtist choseUser(List<String> users) {
		Random random = new Random();
		int number = random.nextInt(users.size());
		String choosenOne = users.get(number);
		return new UserAndArtist(choseArtist(choosenOne), choosenOne);
	}
	
	public static record UserAndArtist(ResponseArtista artist, String user) {}
}
