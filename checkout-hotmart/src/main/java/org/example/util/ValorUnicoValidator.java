package org.example.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {

    private String campoPesquisado;
    private Class<?> tabela;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ValorUnico params) {
        this.campoPesquisado = params.campo();
        this.tabela = params.tabela();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return manager.createQuery("select count(t) < 1 from " + tabela.getName() + " t where "
                        + campoPesquisado + "= :value", Boolean.class)
                .setParameter("value", value)
                .getSingleResult();
    }
}