package it.unibo.mvc;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args)
        throws
        ClassNotFoundException,
        NoSuchMethodException,
        InvocationTargetException,
        InstantiationException,
        IllegalAccessException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        for (final var viewType: List.of("StandardOutput", "Swing")) {
            final var clazz = Class.forName("it.unibo.mvc.view.DrawNumber" + viewType + "View");
            for (int i = 0; i < 3; i++) {
                final var newView = clazz.getConstructor().newInstance();
                if (DrawNumberView.class.isAssignableFrom(newView.getClass())) {
                    app.addView((DrawNumberView) newView);
                } else {
                    throw new IllegalStateException(
                        newView.getClass() + " is not a subclass of " + DrawNumberView.class
                    );
                }
            }
        }
    }
}
