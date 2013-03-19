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
package org.vaadin.addon.portallayout.gwt.client.portal.connection;

import org.vaadin.addon.portallayout.gwt.client.portal.PortalLayoutUtil;
import org.vaadin.addon.portallayout.gwt.client.portal.PortalView;
import org.vaadin.addon.portallayout.gwt.client.portal.StackPortalViewImpl;
import org.vaadin.addon.portallayout.gwt.shared.portal.StackPortalLayoutState;
import org.vaadin.addon.portallayout.gwt.shared.portal.rpc.StackPortalRpc;
import org.vaadin.addon.portallayout.portal.StackPortalLayout;

import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.shared.ui.Connect;

/**
 * StackPortalLayoutConnectore.
 */
@Connect(StackPortalLayout.class)
public class StackPortalLayoutConnector extends PortalLayoutConnector {

    @Override
    public void updatePortletPositionOnServer(ComponentConnector cc) {
        Widget slot = PortalLayoutUtil.getPortletConnectorForContent(cc).getWidget().getAssociatedSlot();
        int positionInView = getView().getWidgetIndex(slot);
        int positionInState = getState().portlets().indexOf(cc);
        if (positionInState != positionInView) {
            getServerRpc().insertPortletAt(cc, positionInView);
        }
    }

    @Override
    protected StackPortalRpc getServerRpc() {
        return (StackPortalRpc)super.getServerRpc();
    }
    
    @Override
    public PortalView getView() {
        return super.getView();
    }
    
    @Override
    protected PortalView initView() {
        return new StackPortalViewImpl(this);
    }

    @Override
    public StackPortalLayoutState getState() {
        return (StackPortalLayoutState)super.getState();
    }
    
    @Override
    protected StackPortalLayoutState createState() {
        return new StackPortalLayoutState();
    }

    @Override
    protected StackPortalRpc initRpc() {
        return RpcProxy.create(StackPortalRpc.class, this);
    }
}
