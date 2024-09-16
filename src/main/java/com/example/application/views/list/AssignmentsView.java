package com.example.application.views.list;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@PermitAll
@Route("assignments")
public class AssignmentsView extends AppLayout {
    private VerticalLayout contentLayout = new VerticalLayout();

    public AssignmentsView() {
        


        List<Assignment> assignments = new ArrayList<>();
        assignments.add(new Assignment("Math", "Calculus", LocalDate.of(2024, 7, 22), 10));
        assignments.add(new Assignment("English", "English 2", LocalDate.of(2024, 7, 22), 20));
        assignments.add(new Assignment("Physics", "Physics C", LocalDate.of(2024, 7, 22), 30));


        Grid<Assignment> grid = new Grid<>(Assignment.class);



        TextField assignmentNameField = new TextField("Assignment Name: ");

        ComboBox<String> subjectComboBox = new ComboBox<>("Subject Name");
        subjectComboBox.setItems("Calculus", "English 2", "Physics C");

        DatePicker datePicker = new DatePicker("Date Due");
        NumberField pointsField = new NumberField("Points");

        pointsField.setMin(0);
        pointsField.setMax(100);

        Button submitButton = new Button("Submit", event -> {
            String assignmentName = assignmentNameField.getValue();
            String subjectName = subjectComboBox.getValue();
            LocalDate dateDue = datePicker.getValue();
            Double points = pointsField.getValue();

            if (assignmentName.isEmpty() || subjectName == null || dateDue == null || points == null) {
                Notification.show("Please fill in all fields");
            } else {
                Assignment newAssignment = new Assignment(assignmentName, subjectName, dateDue, points.intValue());
                assignments.add(newAssignment);
                grid.setItems(assignments);
                clearForm(assignmentNameField, subjectComboBox, datePicker, pointsField);
                Notification.show("Assignment added");
            }
        });

        VerticalLayout formLayout = new VerticalLayout(assignmentNameField, subjectComboBox, datePicker, pointsField, submitButton);



        grid.setColumns("assignmentName", "subjectName", "dateDue", "points");
        grid.setItems(assignments);
        add(grid);
        add(formLayout);
    }

    private void clearForm(TextField assignmentNameField, ComboBox<String> subjectComboBox, DatePicker datePicker, NumberField pointsField) {
        assignmentNameField.clear();
        subjectComboBox.clear();
        datePicker.clear();
        pointsField.clear();
    }
}

