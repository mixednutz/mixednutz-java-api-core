package net.mixednutz.api.core.model;

import java.util.Set;

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
			Set<? extends IGroupSmall> friendGroups) {
		super();
		this.visibilityType = visibilityType;
		this.selectFollowers = selectFollowers;
		this.friendGroups = friendGroups;
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
	}
	/**
	 * Visibility for PRIVATE, ALL_FOLLOWERS, ALL_FRIENDS, ALL_USERS, WORLD.
	 * Other visibility types use a different constructor because they require
	 * more information.
	 * 
	 * @param visibilityType
	 */
	public Visibility(Type visibilityType) {
		this(visibilityType, null, null);
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
		return new Visibility(Type.SELECT_FOLLOWERS, selectFollowers, null);
	}
	/**
	 * VisibilityType.SELECT_FOLLOWERS visibility with set of followers.
	 * 
	 * @param selectFollowers
	 */
	public static Visibility toFriendGroups(Set<? extends IGroupSmall> friendGroups) {
		return new Visibility(Type.FRIEND_GROUPS, null, friendGroups);
	}
	
}
