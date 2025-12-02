package com.example.Views;

import com.example.Interfaces.CredentialsView;
import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.TextBuilder;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class RegisterViewImpl implements CredentialsView {
    private final StackPane root;
    private TextBuilder errorText;

    public RegisterViewImpl(StackPane root) {
        this.root = root;
    }

    @Override
    public void showError(String message) {
        clearError();
        errorText = WidgetFactory.text(message).setFill(Color.RED);
        root.getChildren().add(errorText.getNode());
    }

    @Override
    public void clearError() {
        if (errorText != null) {
            root.getChildren().remove(errorText.getNode());
            errorText = null;
        }
    }
}
