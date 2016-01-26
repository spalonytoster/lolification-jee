package com.mposluszny.lolification.utils;

import com.mposluszny.lolification.core.domain.Player;
import com.mposluszny.lolification.core.domain.Team;
import com.mposluszny.lolification.core.domain.builder.PlayerBuilder;
import com.mposluszny.lolification.core.domain.builder.TeamBuilder;
import com.mposluszny.lolification.rest.dto.PlayerDto;
import com.mposluszny.lolification.rest.dto.TeamDto;

public class LolificationMapper {
	
	public static Team teamDtoToTeam(TeamDto dto) {
		return new TeamBuilder()
				.idTeam(dto.getIdTeam())
				.name(dto.getName())
				.region(dto.getRegion())
				.dateOfEstablishment(dto.getDateOfEstablishment())
					.build();
	}
	
	public static Player playerDtoToPlayer(PlayerDto dto) {
		return new PlayerBuilder()
				.idPlayer(dto.getIdPlayer())
				.name(dto.getName())
				.surname(dto.getSurname())
				.ign(dto.getIgn())
				.role(dto.getRole())
				.isRetired(dto.getRetired())
				.team(new TeamBuilder()
						.name(dto.getTeam())
							.build())
					.build();
	}

}
