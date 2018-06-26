package org.flfmitlab.jhipster5web3j.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Block entity.
 */
public class BlockDTO implements Serializable {

    private Long id;

    @NotNull
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BlockDTO blockDTO = (BlockDTO) o;
        if (blockDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), blockDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BlockDTO{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
