package za.co.bank.atm.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
@Entity
@Table(name = "atm_allocation")
@Getter
@Setter
@NoArgsConstructor
public class Atm_Allocation implements Serializable {



    @Id
    @NonNull
    private int id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "atm_id", referencedColumnName = "atm_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ATM atm_id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "denomination_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Denomination denomination_id;

    @NonNull
    @Column(name = "count")
    private int count;
}
