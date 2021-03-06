package com.medicai.pillpal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Information about using drug in  the elderly
 */
@Entity
@Table(name = "geriatric")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Geriatric implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "geriatric")
    private String geriatric;

    @ManyToOne
    @JsonIgnoreProperties("geriatrics")
    private ApplicationInfo applicationInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeriatric() {
        return geriatric;
    }

    public Geriatric geriatric(String geriatric) {
        this.geriatric = geriatric;
        return this;
    }

    public void setGeriatric(String geriatric) {
        this.geriatric = geriatric;
    }

    public ApplicationInfo getApplicationInfo() {
        return applicationInfo;
    }

    public Geriatric applicationInfo(ApplicationInfo applicationInfo) {
        this.applicationInfo = applicationInfo;
        return this;
    }

    public void setApplicationInfo(ApplicationInfo applicationInfo) {
        this.applicationInfo = applicationInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Geriatric)) {
            return false;
        }
        return id != null && id.equals(((Geriatric) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Geriatric{" +
            "id=" + getId() +
            ", geriatric='" + getGeriatric() + "'" +
            "}";
    }
}
