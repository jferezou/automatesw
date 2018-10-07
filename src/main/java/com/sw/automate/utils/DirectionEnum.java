package com.sw.automate.utils;

public enum DirectionEnum implements CodeEnum<String> {

	HAUT("1"),
	BAS("2");

	/** Le code du type */
	private String code;

	/**
	 * Constructeur
	 *
	 * @param code
	 *            Le code du type
	 */
	private DirectionEnum(final String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}
}
