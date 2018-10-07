package com.sw.automate.utils;

public enum SourisActionEnum implements CodeEnum<String> {

	CLICK("click"),
	DOUBLECLICK("doubleclick");

	/** Le code du type */
	private String code;

	/**
	 * Constructeur
	 *
	 * @param code
	 *            Le code du type
	 */
	private SourisActionEnum(final String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}
}
