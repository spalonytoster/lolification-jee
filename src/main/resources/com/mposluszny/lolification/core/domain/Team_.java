package com.mposluszny.lolification.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-19T19:46:53.823+0100")
@StaticMetamodel(Team.class)
public class Team_ {
	public static volatile SingularAttribute<Team, Long> idTeam;
	public static volatile SingularAttribute<Team, String> name;
	public static volatile SingularAttribute<Team, String> region;
	public static volatile SingularAttribute<Team, String> dateOfEstablishment;
	public static volatile ListAttribute<Team, Player> players;
}
