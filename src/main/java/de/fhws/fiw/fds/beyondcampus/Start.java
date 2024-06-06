package de.fhws.fiw.fds.beyondcampus;

import de.fhws.fiw.fds.sutton.server.AbstractStart;

public class Start extends AbstractStart{

    public static final String CONTEXT_PATH= "beyondcampus";
    public static final String DEMO_CONTEXT_PATH= "demo2";

    public static void main(String[] args) throws Exception {
        new Start().startTomcat();
    }

    @Override
    protected String contextPath(){
        return CONTEXT_PATH;
    }
}
