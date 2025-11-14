package com.example.RendereUI.Widgets;

import com.example.RendereUI.Widget;

import javafx.scene.Node;
import javafx.scene.control.Slider;

public class SliderBuilder extends Widget {
    
    private final Slider _slider;

    public SliderBuilder() {
        _slider = new Slider();
        _slider.setMin(0);
        _slider.setMax(100);
        _slider.setValue(50);
        _slider.setShowTickLabels(false);
        _slider.setShowTickMarks(false);
        _slider.setPrefWidth(2);
        _slider.setPrefHeight(5);
    }

    public SliderBuilder setMin(double value) {
        _slider.setMin(value);
        return this;
    }

    public SliderBuilder setMax(double value) {
        _slider.setMax(value);
        return this;
    }

    public SliderBuilder setValue(double value) {
        _slider.setValue(value);
        return this;
    }

    public SliderBuilder setShowTickLabels(boolean value) {
        _slider.setShowTickLabels(value);
        return this;
    }

    public SliderBuilder setShowTickMarks(boolean value) {
        _slider.setShowTickMarks(value);
        return this;
    }

    public SliderBuilder setLength(double value) {
        _slider.setPrefWidth(value);
        
        return this;
    }

    public SliderBuilder setHeight(double value) {
        _slider.setPrefHeight(value);
        return this;
    }

    @Override
    public Node getNode() {
        return _slider;
    }
}
