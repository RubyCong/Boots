package com.lc.app.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	
	@Getter @Setter @NonNull
	String userName;
	@Getter @Setter @NonNull
	String sex;

}
