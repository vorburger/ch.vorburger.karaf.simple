/*
 * Copyright (c) 2018 Red Hat, Inc. and others. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package ch.vorburger.karaf.simple.shell;

import ch.vorburger.karaf.command.Test;

/**
 * Example of a standalone Shell console that registers Karaf commands WITHOUT OSGi and rest of Karaf.
 *
 * @author Michael Vorburger.ch
 */
public class Main {

    public static void main(String[] args) throws Exception {
        new KarafStandaloneShell().register(Test.class).run();
    }

}
