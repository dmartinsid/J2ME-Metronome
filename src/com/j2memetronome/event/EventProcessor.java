
package com.j2memetronome.event;

import com.j2memetronome.appstate.ApplicationState;
import com.j2memetronome.resource.ResourceLoader;

/**
 *
 * @author Deivid Martins
 */
public interface EventProcessor {

    void processEvent(int eventCode, ApplicationState applicationState, ResourceLoader resourceLoader);

}
