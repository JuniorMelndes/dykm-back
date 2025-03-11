package com.dykm.feature;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dykm.feature.DykmService.UserAndArtist;
import com.dykm.feature.entity.ResponseArtista;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Controller {
	private final DykmService service;
	
	 @GetMapping("/getArtist/{user}")
	 @ResponseStatus(HttpStatus.OK)
	 public ResponseArtista getArtist(final @PathVariable @Size(max = 30) String user) {
		return service.choseArtist(user);
	 }
	 
	 @GetMapping("/getUser")
	 @ResponseStatus(HttpStatus.OK)
	 public UserAndArtist getUser(final @Valid @RequestBody List<String> users){
		 return service.choseUser(users);
	 }
	 
	 @GetMapping("/teste")
	 @ResponseStatus(HttpStatus.OK)
	 public ResponseArtista getArtistTeste() {
		return service.choseArtist("junior_6");
	 }

}
