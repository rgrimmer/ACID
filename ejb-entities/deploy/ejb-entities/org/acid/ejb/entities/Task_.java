package org.acid.ejb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entities.List;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-19T15:47:07")
@StaticMetamodel(Task.class)
public class Task_ { 

    public static volatile SingularAttribute<Task, Integer> idTask;
    public static volatile SingularAttribute<Task, String> description;
    public static volatile SingularAttribute<Task, Integer> priority;
    public static volatile SingularAttribute<Task, String> label;
    public static volatile SingularAttribute<Task, List> idList;
    public static volatile SingularAttribute<Task, Board> idBoard;

}