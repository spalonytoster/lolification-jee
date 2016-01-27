package com.mposluszny.lolification.core.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-19T19:46:53.779+0100")
@StaticMetamodel(Player.class)
public class Player_ {
	public static volatile SingularAttribute<Player, Long> idPlayer;
	public static volatile SingularAttribute<Player, String> name;
	public static volatile SingularAttribute<Player, String> surname;
	public static volatile SingularAttribute<Player, String> ign;
	public static volatile SingularAttribute<Player, String> role;
	public static volatile SingularAttribute<Player, Team> team;
	public static volatile SingularAttribute<Player, Boolean> isRetired;
}
