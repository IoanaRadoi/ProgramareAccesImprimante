/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oana
 */
@Entity
@Table(name = "userprinter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserprinterDB.findAll", query = "SELECT u FROM UserprinterDB u"),
    @NamedQuery(name = "UserprinterDB.findById", query = "SELECT u FROM UserprinterDB u WHERE u.id = :id"),
    @NamedQuery(name = "UserprinterDB.findByUser", query = "SELECT u FROM UserprinterDB u WHERE u.user = :user"),
    @NamedQuery(name = "UserprinterDB.findByPrinter", query = "SELECT u FROM UserprinterDB u WHERE u.printer = :printer"),
    @NamedQuery(name = "UserprinterDB.findByUserAndPrinter", query = "SELECT u FROM UserprinterDB u WHERE u.user = :user and u.printer = :printer")})
public class UserprinterDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "start")
    private String start;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "end")
    private String end;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user")
    private int user;
    @Basic(optional = false)
    @NotNull
    @Column(name = "printer")
    private int printer;

    public UserprinterDB() {
    }

    public UserprinterDB(Integer id) {
        this.id = id;
    }

    public UserprinterDB(Integer id, String start, String end, int user, int printer) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.user = user;
        this.printer = printer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getPrinter() {
        return printer;
    }

    public void setPrinter(int printer) {
        this.printer = printer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserprinterDB)) {
            return false;
        }
        UserprinterDB other = (UserprinterDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.UserprinterDB[ id=" + id + " ]";
    }

}
