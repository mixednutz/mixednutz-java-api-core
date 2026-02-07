package net.mixednutz.api.core.model;

import java.util.Set;

import net.mixednutz.api.model.IExternalRole;
import net.mixednutz.api.model.IGroupSmall;
import net.mixednutz.api.model.IUserSmall;
import net.mixednutz.api.model.IVisibility;


/**
 * Common properties or fields for entities that have visibility attributes.
 * 
 * @see VisibilityType
 * @author apfesta
 *
 */
public class Visibility implements IVisibility {
	
	private Type visibilityType;
	private Set<? extends IUserSmall> selectFollowers;
	private Set<? extends IGroupSmall> friendGroups;
	private Set<? extends IExternalRole> externalGroups;

	/**
	 * Default Constructor
	 */
	public Visibility() {
		super();
	}
	/**
	 * All possible arguments constructor
	 * 
	 * @param visibilityType
	 * @param selectFollowers
	 */
	private Visibility(
			Type visibilityType, 
			Set<? extends IUserSmall> selectFollowers,
			Set<? extends IGroupSmall> friendGroups,
			Set<? extends IExternalRole> externalGroups) {
		super();
		this.visibilityType = visibilityType;
		this.selectFollowers = selectFollowers;
		this.friendGroups = friendGroups;
		this.externalGroups = externalGroups;
		if (Type.SELECT_FOLLOWERS.equals(visibilityType) && 
				(selectFollowers==null||selectFollowers.isEmpty())) {
			throw new IllegalArgumentException(
					"SELECT_FOLLOWERS requires a non-empty set of selectFollowers");
		}
		if (Type.FRIEND_GROUPS.equals(visibilityType) && 
				(friendGroups==null||friendGroups.isEmpty())) {
			throw new IllegalArgumentException(
					"FRIEND_GROUPS requires a non-empty set of friendGroups");
		}
		if (Type.EXTERNAL_GROUP.equals(visibilityType) && 
				(externalGroups==null||externalGroups.isEmpty())) {
			throw new IllegalArgumentException(
					"EXTERNAL_GROUP requires a non-empty set of externalGroups");
		}
	}
	/**
	 * Visibility for PRIVATE, ALL_FOLLOWERS, ALL_FRIENDS, ALL_USERS, WORLD.
	 * Other visibility types use a different constructor because they require
	 * more information.
	 * 
	 * @param visibilityType
	 */
	public Visibility(Type visibilityType) {
		this(visibilityType, null, null, null);
	}
	
	public Type getVisibilityType() {
		return visibilityType;
	}

	public void setVisibilityType(Type visibilityType) {
		this.visibilityType = visibilityType;
	}

	public Set<? extends IUserSmall> getSelectFollowers() {
		return selectFollowers;
	}

	public void setSelectFollowers(Set<UserSmall> selectFollowers) {
		this.selectFollowers = selectFollowers;
	}
	
	public Set<? extends IGroupSmall> getFriendGroups() {
		return friendGroups;
	}
	
	public void setFriendGroups(Set<GroupSmall> friendGroups) {
		this.friendGroups = friendGroups;
	}
	
	public Set<? extends IExternalRole> getExternalGroups() {
		return externalGroups;
	}
	
	public void setExternalGroups(Set<? extends IExternalRole> externalGroups) {
		this.externalGroups = externalGroups;
	}
	
	public static Visibility asPrivate() {
		return new Visibility(Type.PRIVATE);
	}
	public static Visibility toWorld() {
		return new Visibility(Type.WORLD);
	}
	public static Visibility toAllUsers() {
		return new Visibility(Type.ALL_USERS);
	}
	public static Visibility toAllFollowers() {
		return new Visibility(Type.ALL_FOLLOWERS);
	}
	public static Visibility toAllFriends() {
		return new Visibility(Type.ALL_FRIENDS);
	}
	/**
	 * VisibilityType.SELECT_FOLLOWERS visibility with set of followers.
	 * 
	 * @param selectFollowers
	 */
	public static Visibility toSelectFollowers(Set<? extends IUserSmall> selectFollowers) {
		return new Visibility(Type.SELECT_FOLLOWERS, selectFollowers, null, null);
	}
	/**
	 * VisibilityType.SELECT_FOLLOWERS visibility with set of followers.
	 * 
	 * @param selectFollowers
	 */
	public static Visibility toFriendGroups(Set<? extends IGroupSmall> friendGroups) {
		return new Visibility(Type.FRIEND_GROUPS, null, friendGroups, null);
	}
	/**
	 * VisibilityType.SELECT_FOLLOWERS visibility with set of followers.
	 * 
	 * @param selectFollowers
	 */
	public static Visibility toExternalGroups(Set<? extends IExternalRole> externalGroups) {
		return new Visibility(Type.EXTERNAL_GROUP, null, null, externalGroups);
	}
	
}
