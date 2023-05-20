/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Crist
 */
public class Memento {

    private final int state;

    public Memento(int state) {
        this.state = state;
    }

    public int getSavedState() {
        return state;
    }

}
