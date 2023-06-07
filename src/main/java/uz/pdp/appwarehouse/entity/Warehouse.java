package uz.pdp.appwarehouse.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.appwarehouse.entity.template.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Warehouse extends BaseEntity {
}
