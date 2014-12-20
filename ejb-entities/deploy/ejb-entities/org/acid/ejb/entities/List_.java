package org.acid.ejb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entities.Task;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-19T15:47:07")
@StaticMetamodel(List.class)
public class List_ { 

    public static volatile CollectionAttribute<List, Board> boardCollection;
    public static volatile CollectionAttribute<List, Task> taskCollection;
    public static volatile SingularAttribute<List, String> label;
    public static volatile SingularAttribute<List, Integer> idList;

}