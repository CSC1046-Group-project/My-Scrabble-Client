package com.example.RendereUI;

import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.IconButtonBuilder;
import com.example.RendereUI.Widgets.LineBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.TextFieldBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class WidgetFactory {

    public static ButtonBuilder button(String text, EventHandler<ActionEvent> event) {
        return new ButtonBuilder(text, event);
    }

    public static VBoxBuilder vbox() {
        return new VBoxBuilder();
    }

    public static TextBuilder text(String text) {
        return new TextBuilder(text);
    }

    public static LineBuilder line() {
        return new LineBuilder();
    }

    public static TextFieldBuilder textField(String text) {
        return new TextFieldBuilder(text);
    }

    public static HBoxBuilder hbox() {
        return new HBoxBuilder();
    }

    public static IconButtonBuilder iconButton(String iconPath, EventHandler<ActionEvent> event) {
        return new IconButtonBuilder(iconPath, event);
    }

    public static IconButtonBuilder iconButton(String iconPath) {
        return new IconButtonBuilder(iconPath);
    }

    // public static SliderBuilder slider() {
    //     return new SliderBuilder();
    // }
}
