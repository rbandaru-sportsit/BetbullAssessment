package com.sportsit.playermarket.player;

/**
 * Basic class for ITs for one operation of the Rest API.
 */
public abstract class AbstractRestOperationIT {

	/**
	 * Get Rest operation path. For example: /Team/profile
	 *
	 * @return Path
	 */
	protected abstract String getRestOperationPath();

}
