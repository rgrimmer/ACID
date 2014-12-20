package org.acid.ejb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.acid.ejb.entities.List;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entities.Task;
import org.acid.ejb.entities.Type;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-19T15:47:07")
@StaticMetamodel(Board.class)
public class Board_ { 

    public static volatile SingularAttribute<Board, Type> idType;
    public static volatile SingularAttribute<Board, String> name;
    public static volatile CollectionAttribute<Board, Task> taskCollection;
    public static volatile CollectionAttribute<Board, List> listCollection;
    public static volatile SingularAttribute<Board, Project> idProject;
    public static volatile SingularAttribute<Board, Integer> idBoard;

}