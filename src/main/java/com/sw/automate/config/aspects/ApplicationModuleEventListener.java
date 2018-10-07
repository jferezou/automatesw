package com.sw.automate.config.aspects;


import com.sw.automate.annotation.ServiceMethod;

/**
 * Listener associé aux évènements levé par un {@link ApplicationModule}
 */
public interface ApplicationModuleEventListener {
    /**
     * Avant l'invocation d'une méthode de service {@link ServiceMethod}.
     *
     * @param context
     *            le contexte courant d'exécution.
     */
    void beforeServiceMethodExecution(ApplicationModule.Context context);

    /**
     * Après l'invocation d'une méthode de service {@link ServiceMethod}.
     *
     * @param context
     *            le contexte courant d'exécution.
     */
    void afterServiceMethodExecution(ApplicationModule.Context context);
}
