package com.dykm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {
	@JsonProperty("name")
	private String name;
	@JsonProperty("similar")
	private Similar similar;
	@JsonProperty("stats")
	private Status status;
	@JsonProperty("tags")
	private Tag tag;
	@JsonProperty("bio")
	private Bio bio;
	
}
