package org.acid.ejb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.acid.ejb.entities.Board;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-19T15:47:07")
@StaticMetamodel(Type.class)
public class Type_ { 

    public static volatile SingularAttribute<Type, Integer> idType;
    public static volatile CollectionAttribute<Type, Board> boardCollection;
    public static volatile SingularAttribute<Type, String> label;

}