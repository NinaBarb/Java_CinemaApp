/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myProject.model;

/**
 *
 * @author Nina
 */
public interface PersonAddable {
    void addPerson(Person person, int exists) throws Exception;
}
