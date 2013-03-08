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
package org.vaadin.addon.portallayout.gwt.shared.portlet;

import com.vaadin.shared.AbstractComponentState;
import com.vaadin.shared.Connector;

/**
 * PortletExState.
 */
public class PortletExState extends AbstractComponentState {

    public boolean isLocked = false;

    public boolean isCollapsed = false;

    public boolean isClosable = true;

    public boolean isCollapsible = true;
    
    //public SlotSize slotSize = new SlotSize();
    
    public Connector content;
    
    public Connector headerToolbar;
    
}
