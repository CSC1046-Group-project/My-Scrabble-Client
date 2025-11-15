package com.example.SceneManager.Scenes;

import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.IconButtonBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;
import com.example.SceneManager.MyScene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class GameScene extends MyScene {

    @Override
    public void initRoot() {

         // Root container
        _root.setPadding(new Insets(20));
        _root.setBackground(new Background(new BackgroundFill(Color.web("0x16161E"), null, null)));
        _root.setAlignment(Pos.CENTER);

        header();
        boardView();
        VBoxBuilder rightsidePanel = rightSidePanel();

        _root.getChildren().addAll(rightsidePanel.getNode());
    }

    private void header() {

    }

    private void boardView() {

    }

    private VBoxBuilder rightSidePanel() {

        VBoxBuilder panel = WidgetFactory.vbox()
            .setMaxWidth(550)
            .setMaxHeight(850)
            .setPrefWidth(550)
            .setAlignment(Pos.TOP_CENTER);
    
        VBoxBuilder usersBox = WidgetFactory.vbox().setStyle("-fx-background-color: #282833");

        HBoxBuilder user1 = createUserBox("Player1", "/assets/example-profilepic.png").setStyle("-fx-background-color: #282833");
        HBoxBuilder user2 = createUserBox("Player2", "/assets/example-profilepic.png").setStyle("-fx-background-color: #282833");
        HBoxBuilder user3 = createUserBox("Player3", "/assets/example-profilepic.png").setStyle("-fx-background-color: #282833");
        HBoxBuilder user4 = createUserBox("Player4", "/assets/example-profilepic.png").setStyle("-fx-background-color: #282833");

        usersBox.add(user1.getNode(), user2.getNode(), user3.getNode(), user4.getNode());

        VBoxBuilder tilebagBox = WidgetFactory.vbox().setStyle("-fx-background-color: #282833").setSpacing(1);
        HBoxBuilder tiletext = WidgetFactory.hbox().setStyle("-fx-background-color: #282833");

        TextBuilder tilebagText = WidgetFactory.text("Tile bag - 81 tiles left").setFont(16);
        tiletext.add(tilebagText.getNode());

        VBoxBuilder tilesleft = WidgetFactory.vbox().setStyle("-fx-background-color: #282833").setSpacing(2);

        TextBuilder line1 = WidgetFactory.text("A  A  A  A  A  A  A  A  B  C  C  D  D  D  ").setFont(16);
        TextBuilder line2 = WidgetFactory.text("E  E  E  E  E  E  E  E  E  F  F  G  G  G  ").setFont(16);
        TextBuilder line3 = WidgetFactory.text("I  I  I  I  I  I  I  I  I  J  K  L  L  L  L  M  M").setFont(16);
        TextBuilder line4 = WidgetFactory.text("N  N  N  N  N  N  O  O  O  O  O  O  O  P  P").setFont(16);
        TextBuilder line5 = WidgetFactory.text("Q  R  R  R  R  R  S  S  S  T  T  T  T  T").setFont(16);
        TextBuilder line6 = WidgetFactory.text("U  U  U  V  V  W  W  X  Y  Y  Z  ?  ?  ").setFont(16);

        tilesleft.add(line1.getNode(), line2.getNode(), line3.getNode(), line4.getNode(), line5.getNode(), line6.getNode());
        tilebagBox.add(tiletext.getNode(), tilesleft.getNode());

        VBoxBuilder turnhistoryBox = WidgetFactory.vbox().setStyle("-fx-background-color: #282833").setSpacing(2);

        HBoxBuilder turnhistorytext = WidgetFactory.hbox().setStyle("-fx-background-color: #282833");
        TextBuilder turnhistoryText = WidgetFactory.text("Turn History").setFont(16);
        turnhistorytext.add(turnhistoryText.getNode());

        IconButtonBuilder profilePic = WidgetFactory.iconButton(
            "/assets/example-profilepic.png"
        ).setFitWidth(30).setFitHeight(30);

        IconButtonBuilder profilePic2 = WidgetFactory.iconButton(
            "/assets/example-profilepic.png"
        ).setFitWidth(30).setFitHeight(30);

        HBoxBuilder turn1 = WidgetFactory.hbox().setStyle("-fx-background-color: #282833");
        TextBuilder usernameText = WidgetFactory.text("Player1").setFont(16);
        TextBuilder points = WidgetFactory.text("6").setFont(16).setTextAlignment(TextAlignment.RIGHT);
        turn1.add(usernameText.getNode(), profilePic.getNode(), points.getNode());

        HBoxBuilder turn2 = WidgetFactory.hbox().setStyle("-fx-background-color: #282833");
        TextBuilder username2Text = WidgetFactory.text("Player2").setFont(16);
        TextBuilder points2 = WidgetFactory.text("9").setFont(16).setTextAlignment(TextAlignment.RIGHT);
        turn2.add(username2Text.getNode(), profilePic2.getNode(), points2.getNode());

        turnhistoryBox.add(turnhistorytext.getNode(), turn1.getNode(), turn2.getNode());

        panel.addWithFlex(usersBox.getNode(), tilebagBox.getNode(), turnhistoryBox.getNode());

        return panel;
    }    

    // we will change this to user.getUsername() and user.getProfilePicPath() later
    public static HBoxBuilder createUserBox(String username, String profilePicPath) {

            HBoxBuilder allusersBox = WidgetFactory.hbox().setStyle("-fx-background-color: #282833;");
            HBoxBuilder userBox = WidgetFactory.hbox().setStyle("-fx-background-color: #282833;");
            HBoxBuilder statsBox = WidgetFactory.hbox().setStyle("-fx-background-color: #282833;").setAlignment(Pos.CENTER_RIGHT);

            TextBuilder usernameText = WidgetFactory.text(username).setFont(16);

            IconButtonBuilder profilePic = WidgetFactory.iconButton(
                "/assets/example-profilepic.png"
            ).setFitWidth(30).setFitHeight(30);

            // just using this to make the scores different for each user for now
            int n = (Character.getNumericValue(username.charAt(username.length() - 1)));
            int score = 20 + (n * 11) + (n^2 * 2);
            int time = 600 + Math.abs((n * 9282 + 874768) % 801);
            String timeString = String.format("%02d:%02d", time / 60, time % 60);

            TextBuilder scoreText = WidgetFactory.text(String.valueOf(score)).setFont(30);
            TextBuilder timeText = WidgetFactory.text(timeString).setFont(16);

            userBox.add(profilePic.getNode(), usernameText.getNode());
            statsBox.add(scoreText.getNode(), timeText.getNode());

            allusersBox.add(userBox.getNode(), statsBox.getNode());
            return allusersBox;
    }
}
