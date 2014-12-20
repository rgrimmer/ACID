package org.acid.ejb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entities.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-19T15:47:07")
@StaticMetamodel(Project.class)
public class Project_ { 

    public static volatile CollectionAttribute<Project, Board> boardCollection;
    public static volatile SingularAttribute<Project, String> name;
    public static volatile SingularAttribute<Project, Integer> idProject;
    public static volatile CollectionAttribute<Project, User> userCollection;
    public static volatile SingularAttribute<Project, User> idOwner;

}