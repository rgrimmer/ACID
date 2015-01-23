package org.acid.ejb.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "List")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "List.findAll", query = "SELECT l FROM List l"),
    @NamedQuery(name = "List.findByIdList", query = "SELECT l FROM List l WHERE l.idList = :idList"),
    @NamedQuery(name = "List.findByLabel", query = "SELECT l FROM List l WHERE l.label = :label")})
public class List implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_list")
    private Integer idList;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "label")
    private String label;
    @ManyToMany(mappedBy = "listCollection")
    private Collection<Board> boardCollection;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idList")
    private Collection<Task> taskCollection;

    public List() {
    }

    public List(Integer idList) {
        this.idList = idList;
    }

    public List(Integer idList, String label) {
        this.idList = idList;
        this.label = label;
    }

    public Integer getIdList() {
        return idList;
    }

    public void setIdList(Integer idList) {
        this.idList = idList;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @XmlTransient
    public Collection<Board> getBoardCollection() {
        return boardCollection;
    }

    public void setBoardCollection(Collection<Board> boardCollection) {
        this.boardCollection = boardCollection;
    }

    @XmlTransient
    public Collection<Task> getTaskCollection() {
        return taskCollection;
    }

    public void setTaskCollection(Collection<Task> taskCollection) {
        this.taskCollection = taskCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idList != null ? idList.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (idList == null || object == null || !(object instanceof List)) {
            return false;
        }
        List other = (List) object;
        return idList.equals(other.getIdList());
    }

    @Override
    public String toString() {
        return "List[id = " + idList + ",label=" + label + "]";
    }

}
