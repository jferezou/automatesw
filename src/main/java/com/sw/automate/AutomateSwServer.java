package com.sw.automate;

import com.sw.automate.config.ServerComponent;

/**
 * Classe principale
 * @author jferezou
 *
 */
public class AutomateSwServer extends ServerComponent {
	public static void main(final String[] args) {
		run("automatesw", "automatesw", AutomateSwServer.class, args);
	}
}
