package com.PrestaShop.DataResources;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:ProjectData/PathToFileCookie.properties")
public interface GetPathToCookie extends Config {

	@Key("Path")
	String getPathToCookie();
}
