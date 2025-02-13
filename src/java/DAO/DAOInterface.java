/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author Ud
 */
public interface DAOInterface<T> {

    public ArrayList<T> selectAll();

    public T selectById(T t);

    public boolean insert(T t);

    public boolean delete(T t);

    public boolean update(T t);
}
