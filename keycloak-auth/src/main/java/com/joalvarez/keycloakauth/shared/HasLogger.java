package com.joalvarez.keycloakauth.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface HasLogger {

	default Logger getLogger() {
		return LoggerFactory.getLogger(this.getClass());
	}

	default void info(String s) {
		this.getLogger().info(s);
	}

	default void info(String s, Object... objects) {
		this.getLogger().info(s, objects);
	}

	default void warn(String s) {
		this.getLogger().warn(s);
	}

	default void warn(String s, Object... objects) {
		this.getLogger().warn(s, objects);
	}

	default void error(String s) {
		this.getLogger().error(s);
	}

	default void error(String s, Object... objects) {
		this.getLogger().error(s, objects);
	}
}
