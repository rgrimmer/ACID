package org.acid.ejb.entities;

import com.google.common.base.Objects;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findByIdTask", query = "SELECT t FROM Task t WHERE t.idTask = :idTask"),
    @NamedQuery(name = "Task.findByLabel", query = "SELECT t FROM Task t WHERE t.label = :label"),
    @NamedQuery(name = "Task.findByDescription", query = "SELECT t FROM Task t WHERE t.description = :description"),
    @NamedQuery(name = "Task.findByPriority", query = "SELECT t FROM Task t WHERE t.priority = :priority")})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_task")
    private Integer idTask;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "label")
    private String label;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "priority")
    private int priority;
    @Size(min = 0, max = 256)
    @Column(name = "id_sonar")
    private String idSonar;
    @JoinColumn(name = "id_list", referencedColumnName = "id_list")
    @ManyToOne(optional = false)
    private List idList;
    @JoinColumn(name = "id_board", referencedColumnName = "id_board")
    @ManyToOne(optional = false)
    private Board idBoard;

    public Task() {
    }

    public Task(Integer idTask) {
        this.idTask = idTask;
    }

    public Task(Integer idTask, String label, String description, int priority) {
        this.idTask = idTask;
        setLabel(label);
        setDescription(description);
        this.priority = priority;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = trunc(label, 64);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = trunc(description, 256);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public String getIdSonar() {
        return idSonar;
    }

    public void setIdSonar(String idSonar) {
        this.idSonar = idSonar;
    }

    public List getIdList() {
        return idList;
    }

    public void setIdList(List idList) {
        this.idList = idList;
    }

    public Board getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(Board idBoard) {
        this.idBoard = idBoard;
    }
    
    private String trunc(String input, int maxLength) {
        return (input == null || input.length() < maxLength) ? input : input.substring(0, maxLength);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTask != null ? idTask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (idTask == null || object == null || !(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        return idTask.equals(other.getIdTask());
    }

    @Override
    public String toString() {
        return "Task[id = " + idBoard + "]";
    }

}
