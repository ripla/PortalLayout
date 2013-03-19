/*
 * Copyright 2013 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.vaadin.addon.portallayout.gwt.client.portlet;

import org.vaadin.addon.portallayout.gwt.shared.portlet.AbsolutePositionPortletState;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;

/**
 * AbsolutePortletconnctor.
 */
public class AbsolutePortletConnector extends AbstractExtensionConnector {

    private final Element resizeDrag = DOM.createDiv();
    
    @Override
    protected void init() {
        super.init();
    }
    
    @Override
    protected void extend(ServerConnector target) {
        assert target instanceof PortletConnector;
        PortletConnector portlet = (PortletConnector)target;
        portlet.getWidget().getElement().appendChild(resizeDrag);
    }
    
    @Override
    public AbsolutePositionPortletState getState() {
        return (AbsolutePositionPortletState)super.getState();
    }
}
