package org.acid.ejb.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "Board")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Board.findAll", query = "SELECT b FROM Board b"),
    @NamedQuery(name = "Board.findByIdBoard", query = "SELECT b FROM Board b WHERE b.idBoard = :idBoard"),
    @NamedQuery(name = "Board.findByName", query = "SELECT b FROM Board b WHERE b.name = :name")})
public class Board implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_board")
    private Integer idBoard;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "Board_list", joinColumns = {
        @JoinColumn(name = "id_board", referencedColumnName = "id_board")}, inverseJoinColumns = {
        @JoinColumn(name = "id_list", referencedColumnName = "id_list")})
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    private Collection<List> listCollection;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBoard")
    private Collection<Task> taskCollection;
    @JoinColumn(name = "id_type", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private Type idType;
    @JoinColumn(name = "id_project", referencedColumnName = "id_project")
    @ManyToOne(optional = false)
    private Project idProject;

    public Board() {
    }

    public Board(Integer idBoard) {
        this.idBoard = idBoard;
    }

    public Board(Integer idBoard, String name) {
        this.idBoard = idBoard;
        this.name = name;
    }
    
    public Board(String name, Type type, Project project) {    
        this.name = name;
        this.idType = type;
        this.idProject = project;
    }

    public Integer getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(Integer idBoard) {
        this.idBoard = idBoard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<List> getListCollection() {
        return listCollection;
    }

    public void setListCollection(Collection<List> listCollection) {
        this.listCollection = listCollection;
    }

    @XmlTransient
    public Collection<Task> getTaskCollection() {
        return taskCollection;
    }

    public void setTaskCollection(Collection<Task> taskCollection) {
        this.taskCollection = taskCollection;
    }

    public Type getIdType() {
        return idType;
    }

    public void setIdType(Type idType) {
        this.idType = idType;
    }

    public Project getIdProject() {
        return idProject;
    }

    public void setIdProject(Project idProject) {
        this.idProject = idProject;
    }
    
    public org.acid.ejb.entities.List getFirstList() {
        // 'TO DO' for Sprint, 'TOFIX' for BugReport, 'TO DO NEXT' for Backlog
        for (org.acid.ejb.entities.List l : listCollection) {
            if (l.getLabel().startsWith("TODO") || l.getLabel().equals("TOFIX")) {
                return l;
            }
        }
        return null;
    }
    
    public void addList(List list) {
        System.out.println("###########################> " + listCollection.size());
        listCollection.add(list);
        System.out.println("########################### AFTER> " + listCollection.size());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBoard != null ? idBoard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (idBoard == null || object == null || !(object instanceof Board)) {
            return false;
        }
        Board other = (Board) object;
        return idBoard.equals(other.getIdBoard());
    }

    @Override
    public String toString() {
        return "Board[id = " + idBoard + ",name=" + name + "]";
    }

}
