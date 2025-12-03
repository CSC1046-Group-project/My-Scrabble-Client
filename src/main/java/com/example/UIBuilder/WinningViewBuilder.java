package com.example.UIBuilder;

import com.example.Controllers.ChooseController;
import com.example.Game.Game;
import com.example.Interfaces.INavigationService;
import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;

import javafx.scene.Node;

public class WinningViewBuilder {

    private final INavigationService _navigationService;

    public WinningViewBuilder(
        INavigationService navigationService
    ) {
        _navigationService = navigationService;
    }

    public Node build() {

        // Centered Panel
        VBoxBuilder panel = WidgetFactory.vbox()
            .setMaxWidth(550)
            .setMaxHeight(550)
            .setPrefWidth(550);

        // Winner text
        TextBuilder winner = WidgetFactory.text(Game.getWinner() + " won the game!").setFont(36);

        // Go back to lobby button
        ButtonBuilder lobbyButton = WidgetFactory.button(
            "Go back to lobby",
            e -> _navigationService.navigateToChooseScene()
        );

        panel.addWithFlex(winner.getNode(), lobbyButton.getNode());
        return panel.getNode();
    }
}
