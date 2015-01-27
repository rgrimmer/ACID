/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acid.ejb.sonartasks;

import java.util.List;
import javax.ejb.Remote;

import org.acid.ejb.entities.Task;
/**
 *
 * @author maxence
 */
@Remote
public interface SonarTasks {
    
    List<Task> getTasks();
    
}
