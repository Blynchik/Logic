package com.logic.game.model.fighter;

import com.logic.game.model.db.AppUser;
import lombok.Data;


/**
 * Класс Fighter представляет сущность бойца.
 *
 * @Data - аннотация Lombok, которая автоматически генерирует геттеры, сеттеры, toString, equals и hashCode.
 */
@Data
public class Fighter {
    private String name;
    private String surname;
    private AppUser appUser;
    private Characteristics characteristics;
    private Attributes attributes;
    private String description;
    private Boolean isAttacker;
    private Integer currentHp;

    /**
     * Конструктор класса Fighter с параметрами.
     * Использован при мапинге героя в бойца.
     *
     * @param name            - имя бойца типа String
     * @param surname         - фамилия бойца типа String
     * @param appUser         - пользователь, которому принадлежит боец типа AppUser
     * @param characteristics - характеристики бойца типа Characteristics
     * @param attributes      - атрибуты бойца типа Attributes
     * @param currentHp       - текущее количество очков здоровья бойца типа Integer
     */
    public Fighter(String name,
                   String surname,
                   AppUser appUser,
                   Characteristics characteristics,
                   Attributes attributes,
                   Integer currentHp) {
        this.name = name;
        this.surname = surname;
        this.appUser = appUser;
        this.characteristics = characteristics;
        this.attributes = attributes;
        this.currentHp = currentHp;
    }

    /**
     * Конструктор класса Fighter с параметрами.
     * Использован при мапинге противника в бойца.
     *
     * @param name            - имя бойца типа String
     * @param characteristics - характеристики бойца типа Characteristics
     * @param attributes      - атрибуты бойца типа Attributes
     * @param description     - описание бойца типа String
     * @param currentHp       - текущее количество очков здоровья бойца типа Integer
     */
    public Fighter(String name,
                   Characteristics characteristics,
                   Attributes attributes,
                   String description,
                   Integer currentHp) {
        this.name = name;
        this.characteristics = characteristics;
        this.attributes = attributes;
        this.description = description;
        this.currentHp = currentHp;
    }

    /**
     * Конструктор класса Fighter с параметром типа Fighter.
     * Использован при копировании бойца.
     * @param fighter - объект класса Fighter
     */
    public Fighter(Fighter fighter) {
        this.name = fighter.getName();
        this.surname = fighter.getSurname();
        this.appUser = fighter.getAppUser();
        this.characteristics = new Characteristics(fighter.getCharacteristics());
        this.attributes = new Attributes(fighter.getAttributes());
        this.description = fighter.getDescription();
        this.isAttacker = fighter.getIsAttacker();
        this.currentHp = fighter.getCurrentHp();
    }

    /**
     * Конструктор класса Fighter с параметрами.
     * Использован при обновлении хп бойца.
     *
     * @param fighter - объект класса Fighter
     * @param characteristics - характеристики бойца типа Characteristics
     * @param attributes - атрибуты бойца типа Attributes
     * @param currentHp - текущее количество очков здоровья бойца типа Integer
     */
    public Fighter(Fighter fighter,
                   Characteristics characteristics,
                   Attributes attributes,
                   Integer currentHp) {
        this.name = fighter.getName();
        this.surname = fighter.getSurname();
        this.appUser = fighter.getAppUser();
        this.characteristics = characteristics;
        this.attributes = attributes;
        this.description = fighter.getDescription();
        this.isAttacker = fighter.getIsAttacker();
        this.currentHp = currentHp;
    }

    /**
     * Конструктор класса Fighter с параметрами.
     * Использован при обновлении характеристик и атрибутов бойца.
     *
     * @param fighter - объект класса Fighter
     * @param characteristics - характеристики бойца типа Characteristics
     * @param attributes - атрибуты бойца типа Attributes
     */
    public Fighter(Fighter fighter,
                   Characteristics characteristics,
                   Attributes attributes) {
        this.name = fighter.getName();
        this.surname = fighter.getSurname();
        this.appUser = fighter.getAppUser();
        this.characteristics = characteristics;
        this.attributes = attributes;
        this.description = fighter.getDescription();
        this.isAttacker = fighter.getIsAttacker();
        this.currentHp = fighter.getCurrentHp();
    }
}
