/*
 * Copyright (c) 2018 Red Hat, Inc. and others. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package ch.vorburger.karaf.simple.shell;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.apache.karaf.shell.api.action.lifecycle.Manager;
import org.apache.karaf.shell.api.console.Session;
import org.apache.karaf.shell.impl.action.command.ManagerImpl;

/**
 * Karaf standalone shell Main with programmatic instead of file-based command registration.
 *
 * @author Michael Vorburger.ch
 */
public class KarafStandaloneShell {

    private final InnerMain karafMain = new InnerMain();

    private final Queue<Class<?>> actionClasses = new ConcurrentLinkedDeque<>();

    /**
     * Register a service.
     * If the given class is an {@link org.apache.karaf.shell.api.action.Action},
     * a {@link org.apache.karaf.shell.api.console.Command} will be created and registered,
     * else, an instance of the class will be created, injected and registered.
     *
     * @param clazz the Action class to register.
     */
    // JavaDoc and signature as in org.apache.karaf.shell.api.action.lifecycle.Manager.register(Class<?>), but without unregister!
    public KarafStandaloneShell register(Class<?> clazz) {
        actionClasses.add(clazz);
        return this;
    }

    public void run() throws Exception {
        karafMain.run(new String[0]);
    }

    private class InnerMain extends org.apache.karaf.shell.impl.console.standalone.Main {
        @Override
        protected void discoverCommands(Session session, ClassLoader cl, String resource) throws IOException, ClassNotFoundException {
            Manager manager = new ManagerImpl(session.getRegistry(), session.getFactory().getRegistry(), true);
            for (Class<?> clazz : actionClasses) {
                manager.register(clazz);
            }
        }
    }
}
