package com.rendezvous.entity;

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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "conversation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conversation.findAll", query = "SELECT c FROM Conversation c")
    , @NamedQuery(name = "Conversation.findById", query = "SELECT c FROM Conversation c WHERE c.id = :id")})
public class Conversation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "user1_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user1Id;
    @JoinColumn(name = "user2_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user2Id;

    public Conversation() {
    }

    public Conversation(Integer id) {
        this.id = id;
    }

    public Conversation(User user1Id, User user2Id) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(User user1Id) {
        this.user1Id = user1Id;
    }

    public User getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(User user2Id) {
        this.user2Id = user2Id;
    }

    public User getPartnerId(int userId) {
        if (user1Id.getId() == userId) {
            return user2Id;
        } else {
            return user1Id;
        }
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
        if (!(object instanceof Conversation)) {
            return false;
        }
        Conversation other = (Conversation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rendezvous.entity.Conversation[ id=" + id + " ]";
    }

}
