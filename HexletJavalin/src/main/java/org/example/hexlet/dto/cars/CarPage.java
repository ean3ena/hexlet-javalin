package org.example.hexlet.dto.cars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.hexlet.model.Car;

@AllArgsConstructor
@Getter
public class CarPage {
    private Car car;
}
