package dev.dashaun.ui.calendar.views.eventform;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import dev.dashaun.ui.calendar.client.CalendarClient;
import dev.dashaun.ui.calendar.views.Event;
import dev.dashaun.ui.calendar.views.MainLayout;

@PageTitle("Event Form")
@Route(value = "event-form", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class EventFormView extends Div {

    private final TextField name = new TextField("Name");
    private final DatePicker startDate = new DatePicker("Start");
    private final DatePicker endDate = new DatePicker("End");
    private final TextField url = new TextField("URL");
    private final TextField content = new TextField("Info");

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private final Binder<Event> binder = new Binder<>(Event.class);

    public EventFormView(CalendarClient calendarClient) {
        addClassName("event-form-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            calendarClient.createEvent(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new Event());
    }

    private Component createTitle() {
        return new H3("Calendar Event");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(startDate, endDate, name, url, content);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

}
