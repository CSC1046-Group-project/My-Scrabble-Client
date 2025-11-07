package com.example.RendereUI;

import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.LineBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class WidgetFactory {

    public static ButtonBuilder button(String text, EventHandler<ActionEvent> event) {
        return new ButtonBuilder(text, event);
    }

    public static VBoxBuilder panel() {
        return new VBoxBuilder();
    }

    public static TextBuilder text(String text) {
        return new TextBuilder(text);
    }

    public static LineBuilder line() {
        return new LineBuilder();
    }
}
