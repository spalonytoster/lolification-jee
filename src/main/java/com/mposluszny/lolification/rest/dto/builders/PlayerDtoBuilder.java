package com.mposluszny.lolification.rest.dto.builders;

import com.mposluszny.lolification.core.domain.Player;
import com.mposluszny.lolification.rest.dto.PlayerDto;

public class PlayerDtoBuilder {
	
	public PlayerDtoBuilder() { }
	
	public PlayerDtoBuilder(Player player) {
		
		this.player = new PlayerDtoBuilder()
				.idPlayer(player.getIdPlayer())
						.name(player.getName())
						.ign(player.getIgn())
						.surname(player.getSurname())
						.role(player.getRole())
						.isRetired(player.getRetired())
						.build();
		if (player.getTeam() != null) {
			this.player.setTeam(player.getTeam().getName());
		}
		
	}
	
	private PlayerDto player = new PlayerDto();
	
	public PlayerDtoBuilder idPlayer(long idPlayer) {
		player.setIdPlayer(idPlayer);
		return this;
	}
	
	public PlayerDtoBuilder name(String name) {
		player.setName(name);
		return this;
	}
	
	public PlayerDtoBuilder surname(String surname) {
		player.setSurname(surname);
		return this;
	}
	
	public PlayerDtoBuilder ign(String ign) {
		player.setIgn(ign);
		return this;
	}
	
	public PlayerDtoBuilder role(String role) {
		player.setRole(role);
		return this;
	}
	
	public PlayerDtoBuilder team(String team) {
		player.setTeam(team);
		return this;
	}
	
	public PlayerDtoBuilder isRetired(boolean isRetired) {
		player.setRetired(isRetired);
		return this;
	}

	public PlayerDto build() {
		return player;
	}

}
