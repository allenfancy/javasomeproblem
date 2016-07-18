package com.allenfancy.email.submail.util;

import java.util.Properties;

import com.allenfancy.email.submail.config.MailConfig;
import com.allenfancy.email.submail.config.MessageConfig;
import com.babailiren.config.AppConfig;

public class ConfigLoader {

	private static Properties pros = null;

	static {
		pros = new Properties();
		try {
			pros.load(ConfigLoader.class.getResourceAsStream("../app.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static enum ConfigType {
		Mail,Message
	}

	public static AppConfig load(ConfigType type) {
		switch (type) {
		case Mail:
			return createMailConfig();
		case Message:
			return createMessageConfig();
		default:
			return null;
		}
	}

	private static AppConfig createMessageConfig() {
		AppConfig config = new MessageConfig();
		config.setAppId(pros.getProperty(MessageConfig.APP_ID));
		config.setAppKey(pros.getProperty(MessageConfig.APP_KEY));
		config.setSignType(pros.getProperty(MessageConfig.APP_SIGNTYPE));
		return config;
	}
	private static AppConfig createMailConfig() {

		AppConfig config = new MailConfig();
		config.setAppId(pros.getProperty(MailConfig.APP_ID));
		config.setAppKey(pros.getProperty(MailConfig.APP_KEY));
		config.setSignType(pros.getProperty(MailConfig.APP_SIGNTYPE));
		return config;
	}
}
