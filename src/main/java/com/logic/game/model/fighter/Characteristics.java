package com.logic.game.model.fighter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Characteristics {
    private Integer strength;
    private Integer dexterity;
    private Integer constitution;

    public Characteristics(Characteristics characteristics){
        this.strength = characteristics.getStrength();
        this.dexterity = characteristics.getDexterity();
        this.constitution = characteristics.getConstitution();
    }
}
