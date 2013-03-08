package org.vaadin.addon.portallayout.demo;

import org.vaadin.addon.portallayout.demo.DemoTable.NameType;
import org.vaadin.addon.portallayout.portal.Portal;
import org.vaadin.addon.portallayout.portlet.Portlet;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class ActionDemoTab extends Panel /*implements PortletCloseListener, PortletCollapseListener*/ {
   
    public class DemoPortal extends Portal {
        
        public DemoPortal() {
            setWidth("100%");
            setHeight("800px");
            //addCloseListener(ActionDemoTab.this);
            //addCollapseListener(ActionDemoTab.this);
            setMargin(true);
        }
    }
    
    private final Portal videoPortal = new DemoPortal() {
        @Override
        public Portlet wrapInPortlet(Component c/*, int position*/) {
            //clearPortletStyleNames(c);
            //addPortletStyleName(c, "red");
            c.setHeight("300px");
            Portlet p = super.wrapInPortlet(c/*, position*/);
            p.setHeight("300px");
            return p;
        };
    };
    
    private final Portal imagePortal = new DemoPortal()  {
        @Override
        public Portlet wrapInPortlet(Component c/*, int position*/) {
            //clearPortletStyleNames(c);
            //addPortletStyleName(c, "green");
            c.setHeight("300px");
            Portlet p = super.wrapInPortlet(c/*, position*/);
            p.setHeight("300px");
            return p;
        };
    };
    
    private final Portal miscPortal = new DemoPortal()  {
        @Override
        public Portlet wrapInPortlet(Component c/*, int position*/) {
            
            c.setHeight("300px");
            Portlet p = super.wrapInPortlet(c/*, position*/);
            p.setHeight("300px");
            return p;
            //clearPortletStyleNames(c);
            //addPortletStyleName(c, "yellow");
        };
    };
    
    private final GridLayout layout = new GridLayout(3, 1);
    
    private boolean init = false;
    
    public ActionDemoTab() {
        super();
        setSizeFull();
        setContent(layout);
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setWidth("100%");
        buildPortals();
        construct();
    }

    private void buildPortals() {
        layout.addComponent(videoPortal, 0, 0);
        layout.addComponent(imagePortal, 1, 0);
        layout.addComponent(miscPortal, 2, 0);
    }
    

    public void construct() {
        if (init)
            return;
        init = true;
        createVideoContents();
        createImageContents();
        createMiscContents();
    }
    
    private void createMiscContents() {
        final DemoTable table = new DemoTable();
        miscPortal.wrapInPortlet(table);
        table.setCaption("Artists");
        table.setIcon(new ThemeResource("chart.png"));
        
        final HorizontalLayout header =  new HorizontalLayout();
        final TextField filterField = new TextField();
        final NativeSelect filterType = new NativeSelect();
        final Label caption = new Label("Filter: ");
        for (final NameType t : NameType.values()) {
            filterType.addItem(t);
        }
        filterType.setValue(NameType.NT_FIRST_NAME);
        caption.addStyleName("v-white-text");
        filterField.setImmediate(true);
        header.setSizeUndefined();
        header.addComponent(caption);
        header.addComponent(filterField);
        header.addComponent(filterType);
        header.setSpacing(true);
        header.setComponentAlignment(caption, Alignment.MIDDLE_LEFT);
        header.setComponentAlignment(filterType, Alignment.MIDDLE_LEFT);
        miscPortal.getPortlet(table).setHeaderToolbar(header);
        filterField.addListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                table.filter((NameType)filterType.getValue(), filterField.getValue().toString());
            }
        });
    }

    private void createImageContents() {
        final PortalImage image = new PortalImage();
        imagePortal.wrapInPortlet(image);
        /*final RatingStars rating = new RatingStars();
        rating.setImmediate(true);
        rating.addListener(new ValueChangeListener() {            
            @Override
            public void valueChange(ValueChangeEvent event) {
                if (rating.getValue() != null) {
                    image.setRating((Double)rating.getValue());
                }
            }
        });*/
        //imagePortal.setHeaderComponent(image, rating);
        /*imagePortal.addAction(image, new ToolbarAction(new ThemeResource("arrow_right.png")) {
            @Override
            public void execute(final Context context) {
                if (!image.isEmpty()) {
                    image.showNextFile();
                    final Component header = context.getPortal().getHeaderComponent(image);
                    if (header instanceof Field) {
                        ((Field) header).setValue(image.getRating());
                    }
                }
            }
        });
        imagePortal.addAction(image, new ToolbarAction(new ThemeResource("arrow_left.png")) {
            @Override
            public void execute(final Context context) {
                if (!image.isEmpty()) {
                    image.showPrevFile();
                    final Component header = context.getPortal().getHeaderComponent(image);
                    if (header instanceof Field) {
                        ((Field) header).setValue(image.getRating());
                    }
                }
            }
        });*/
    }
    
    private void createVideoContents() {
        
        Embedded pl = new Embedded(null, new ExternalResource(
                "http://www.youtube.com/v/meXvxkn1Y_8&hl=en_US&fs=1&"));
        pl.setAlternateText("Vaadin Eclipse Quickstart video");
        pl.setMimeType("application/x-shockwave-flash");
        pl.setParameter("allowFullScreen", "true");
        pl.setHeight("100%");
        pl.setImmediate(true);
        videoPortal.wrapInPortlet(pl);
        pl.setCaption("Joy Division - Disorder");
        pl.setIcon(new ThemeResource("video.png"));
        
        final HorizontalLayout header =  new HorizontalLayout();
        final TextField idField = new TextField();
        final Label caption = new Label("Enter video id: ");
        caption.addStyleName("v-white-text");
        idField.setImmediate(true);
        header.setSizeUndefined();
        header.addComponent(caption);
        header.addComponent(idField);
        header.setSpacing(true);
        header.setComponentAlignment(caption, Alignment.MIDDLE_LEFT);
        
        idField.addListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                //pl.setVideoId(idField.getValue().toString());
            }
        });
        
        /*videoPortal.setHeaderComponent(pl, header);
        videoPortal.addAction(pl, new ToolbarAction(new ThemeResource("stop.png")) {
            @Override
            public void execute(final Context context) {
                pl.stop();
                final Notification n = new Notification("Stop! If didn't stop - DO NOT use YouTube add-on and FF!");
                n.setDelayMsec(1000);
                getWindow().showNotification(n);
            }
        });
        
        videoPortal.addAction(pl, new ToolbarAction(new ThemeResource("pause.png")) {
            @Override
            public void execute(final Context context) {
                pl.pause();
                final Notification n = new Notification("Pause! If didn't pause - DO NOT use YouTube add-on and FF!");
                n.setDelayMsec(1000);
                getWindow().showNotification(n);
            }
        });
        
        videoPortal.addAction(pl, new ToolbarAction(new ThemeResource("play.png")) {
            @Override
            public void execute(final Context context) {
                pl.requestRepaint();
                pl.play();
                final Notification n = new Notification("Play! If didn't start - DO NOT use YouTube add-on and FF!");
                n.setDelayMsec(1000);
                getWindow().showNotification(n);
            }
        });*/
    }


    /*@Override
    public void portletCollapseStateChanged(PortletCollapseEvent event) {
        final Context context = event.getContext();
        getWindow().showNotification(context.getComponent().getCaption() + "collapsed " + 
                context.getPortal().isCollapsed(context.getComponent()));
    }

    @Override
    public void portletClosed(PortletClosedEvent event) {
        getWindow().showNotification(event.getContext().getComponent().getCaption() + "closed");
    }*/

}