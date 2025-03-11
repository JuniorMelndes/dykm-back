package com.dykm.feature.entity;
import java.util.List;

import com.dykm.entity.Artist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseArtista {
	private String name;
	private String listerners;
	private String scrobbles;
	private int position;
	private String birthOrFoundation;
	private List<String> similars;
	private List<String> genre;
	
}
