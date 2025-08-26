package org.example.rdv_app.dao.utils;

import jakarta.persistence.Table;

@Table(name = "enum")
public enum Statut {
    CONFIRME
    , ATTENTE
    , ANNULE
}
