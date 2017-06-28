package com.opaulochaves.auginc.domain.employee.authorities;

import com.opaulochaves.auginc.domain.employee.Employee;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author paulo
 */
@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {
    
    public static final String ROLE_PREFIX = "ROLE_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Version
    private Integer version;
    
    @Column(name = "name", length = 50, unique = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorityName authorityName;
    
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<Employee> employees;
    
    public Authority() {
    }

    public Authority(AuthorityName authorityName) {
        this.setAuthorityName(authorityName);
    }
    
    @Override
    public String getAuthority() {
        return authorityName.name();
    }
    
}
