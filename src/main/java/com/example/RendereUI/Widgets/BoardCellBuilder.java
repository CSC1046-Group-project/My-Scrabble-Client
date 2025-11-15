package com.example.RendereUI.Widgets;

import java.io.InputStream;

import com.example.Game.BoardCell;
import com.example.RendereUI.Widget;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BoardCellBuilder extends Widget {

    private final StackPane _cellPane;

    public BoardCellBuilder(int x, int y, BoardCell.TypePower power) {

        _cellPane = new StackPane();
        _cellPane.setPrefSize(50, 50);
        _cellPane.setStyle("-fx-background-color: #FFDA9E; -fx-background-radius: 10;");

        switch (power) {
            case NONE:
                _cellPane.setStyle("-fx-background-color: #C5C5D2; -fx-background-radius: 10;");
                break;
            case MIDDLE:
                setIcon("/assets/star.png");
                _cellPane.setStyle("-fx-background-color: #C5C5D2; -fx-background-radius: 10;");
                break;
            case TW:
                setText("TW");
                _cellPane.setStyle("-fx-background-color: #C04D4D; -fx-background-radius: 10;");
                break;
            case DW:
                setText("DW");
                _cellPane.setStyle("-fx-background-color: #E5A3A4; -fx-background-radius: 10;");
                break;
            case TL:
                setText("TL");
                _cellPane.setStyle("-fx-background-color: #04679D; -fx-background-radius: 10;");
                break;
            case DL:
                setText("DL");
                _cellPane.setStyle("-fx-background-color: #68A3C4; -fx-background-radius: 10;");
                break;
            default:
        }

        _cellPane.setLayoutX(x);
        _cellPane.setLayoutY(y);
    }

    private void setText(String text) {
        Text t = new Text(text);
        t.setFont(Font.font(24));
        StackPane.setAlignment(t, Pos.CENTER);
        _cellPane.getChildren().addAll(t);
    }

    private void setIcon(String path) {
        InputStream iconStream = getClass().getResourceAsStream(path);
        if (iconStream != null) {
            Image icon = new Image(iconStream);
            ImageView iconView = new ImageView(icon);
            iconView.setFitWidth(32);
            iconView.setFitHeight(32);
            iconView.setPreserveRatio(true);
            StackPane.setAlignment(iconView, Pos.CENTER);
            _cellPane.getChildren().add(iconView);
        } else {
            System.err.println("YAAAAAAAAAAAAAAAA");
        }
    }

    @Override
    public Node getNode() {
        return _cellPane;
    }
}
